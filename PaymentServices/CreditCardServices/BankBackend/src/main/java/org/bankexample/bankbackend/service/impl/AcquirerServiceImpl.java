package org.bankexample.bankbackend.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bankexample.bankbackend.dto.payment.InitiatePaymentDTO;
import org.bankexample.bankbackend.dto.payment.CreatePaymentDTO;
import org.bankexample.bankbackend.dto.payment.PaymentCreatedResponseDTO;
import org.bankexample.bankbackend.dto.payment.PaymentResultResponseDTO;
import org.bankexample.bankbackend.dto.transaction.TransactionRequestDTO;
import org.bankexample.bankbackend.dto.transaction.TransactionResultResponseDTO;
import org.bankexample.bankbackend.exception.PaymentDoesNotExistException;
import org.bankexample.bankbackend.exception.PaymentParametersBadRequestException;
import org.bankexample.bankbackend.mapper.PaymentMapper;
import org.bankexample.bankbackend.mapper.TransactionMapper;
import org.bankexample.bankbackend.model.Payment;
import org.bankexample.bankbackend.model.PaymentRedirectUrls;
import org.bankexample.bankbackend.model.TransactionResult;
import org.bankexample.bankbackend.repository.PaymentRedirectUrlsRepository;
import org.bankexample.bankbackend.repository.PaymentRepository;
import org.bankexample.bankbackend.service.AcquirerService;
import org.bankexample.bankbackend.service.MerchantService;
import org.bankexample.bankbackend.service.TransactionService;
import org.bankexample.bankbackend.service.CardService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import static org.bankexample.bankbackend.util.constants.PaymentConstants.PAYMENT_URL;
import static org.bankexample.bankbackend.util.constants.PaymentConstants.PCC_URL;

@RequiredArgsConstructor
@Service
@Slf4j
public class AcquirerServiceImpl implements AcquirerService {

    private final PaymentRepository paymentRepository;
    private final PaymentRedirectUrlsService paymentRedirectUrlsService;
    private final MerchantService merchantService;
    private final TransactionService transactionService;
    private final CardService cardService;
    private final PaymentMapper paymentMapper;
    private final TransactionMapper transactionMapper;

    @Override
    public PaymentCreatedResponseDTO createPayment(CreatePaymentDTO dto) {

        merchantService.authenticateMerchant(dto.getMerchantId(), dto.getMerchantPassword());

        Payment payment = paymentMapper.mapToPayment(dto);
        payment = paymentRepository.save(payment);
        paymentRedirectUrlsService.save(payment.getId(), dto.getSuccessUrl(), dto.getFailedUrl(), dto.getErrorUrl());
        String paymentUrl = String.format("%s/%s/%s", PAYMENT_URL, payment.getId(), payment.getMerchantId());

        return paymentMapper.mapToPaymentCreatedResponseDTO(payment, paymentUrl, dto.getSuccessUrl(), dto.getFailedUrl(), dto.getErrorUrl());

    }

    @Override
    public PaymentResultResponseDTO initiatePayment(InitiatePaymentDTO dto) {

        Payment payment = paymentRepository.findById(UUID.fromString(dto.getPaymentId()))
                .orElseThrow(PaymentDoesNotExistException::new);
        validateParametersForInitiatingPayment(dto, payment);

        payment.setAcquirerOrderId(UUID.randomUUID().toString());
        payment.setAcquirerTimestamp(LocalDateTime.now().toInstant(ZoneOffset.UTC).toString());
        payment.setCardNumber(dto.getCardNumber());
        payment.setPaymentAttempted(true);
        paymentRepository.save(payment);

        TransactionRequestDTO transactionRequestDTO = transactionMapper.mapInitiatePaymentDTOToTransactionRequestDTO(
                dto, payment, merchantService.getMerchantAccountNumber(payment.getMerchantId()));
        PaymentResultResponseDTO response;
        TransactionResultResponseDTO transactionResultDTO;
        if (cardService.clientInSameBank(dto.getCardNumber())) {
            transactionResultDTO =  transactionService.holdFunds(transactionRequestDTO);
            paymentRepository.save(paymentMapper.updateIssuerAcquirerFields(payment, transactionResultDTO));
        } else {
            transactionResultDTO = forwardToPCC(transactionRequestDTO);
            paymentRepository.save(paymentMapper.updateIssuerAcquirerFields(payment, transactionResultDTO));
        }

        response = paymentMapper.mapToPaymentResultResponseDTO(transactionResultDTO, String.valueOf(payment.getId()));
        response.setRedirectUrl(paymentRedirectUrlsService.getUrlForPaymentId(payment.getId(), transactionResultDTO.getTransactionResult()));
        return response;
    }

    @Override
    public void validateParametersForInitiatingPayment(InitiatePaymentDTO dto, Payment payment) {
        if (payment.isPaymentAttempted()) {
            throw new PaymentParametersBadRequestException("Payment has already been tried");
        }
        if (!dto.getMerchantId().equals(payment.getMerchantId())) {
            throw new PaymentParametersBadRequestException("Invalid merchant id");
        }
        // Card number validation Luhn algorithm TODO
    }

    @Override
    public TransactionResultResponseDTO forwardToPCC(TransactionRequestDTO requestDTO) {
        TransactionRequestDTO dto = requestDTO.toBuilder().build();
        try {
            String endpointUrl = String.format("%s/api/payment", PCC_URL);

            TransactionResultResponseDTO response = WebClient.create()
                    .post()
                    .uri(endpointUrl)
                    .bodyValue(dto)
                    .retrieve()
                    .bodyToMono(TransactionResultResponseDTO.class)
                    .onErrorResume(e -> {
                        log.error("Error occurred while calling PCC: {}", e.getMessage(), e);
                        String message = e.getMessage();
                        if (e.getMessage().startsWith("Connection refused")) {
                            message = "Connection to PCC refused";
                        }
                        return Mono.just(new TransactionResultResponseDTO(
                                TransactionResult.ERROR,
                                requestDTO.getMerchantId(),
                                requestDTO.getMerchantOrderId(),
                                requestDTO.getAcquirerOrderId(),
                                requestDTO.getAcquirerTimestamp(),
                                message));
                    })                    .block();
            if (response != null) {
                log.info(response.toString());
                return response;
            } else {
                throw new IllegalArgumentException("Response from bank is null.");
            }
        } catch (Exception ex) {
            log.info(ex.getMessage());
            // TODO change message to generic
            return new TransactionResultResponseDTO(TransactionResult.ERROR, requestDTO.getMerchantId(), requestDTO.getMerchantOrderId(), requestDTO.getAcquirerOrderId(), requestDTO.getAcquirerTimestamp(), ex.getMessage());
        }
    }


}
