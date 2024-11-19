package org.bankexample.bankbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.bankexample.bankbackend.dto.payment.InitiatePaymentDTO;
import org.bankexample.bankbackend.dto.payment.CreatePaymentDTO;
import org.bankexample.bankbackend.dto.payment.PaymentCreatedResponseDTO;
import org.bankexample.bankbackend.dto.payment.PaymentResultResponseDTO;
import org.bankexample.bankbackend.dto.transaction.TransactionRequestDTO;
import org.bankexample.bankbackend.dto.transaction.TransactionResultResponseDTO;
import org.bankexample.bankbackend.mapper.PaymentMapper;
import org.bankexample.bankbackend.mapper.TransactionMapper;
import org.bankexample.bankbackend.model.Payment;
import org.bankexample.bankbackend.model.PaymentRedirectUrls;
import org.bankexample.bankbackend.repository.PaymentRedirectUrlsRepository;
import org.bankexample.bankbackend.repository.PaymentRepository;
import org.bankexample.bankbackend.service.AcquirerService;
import org.bankexample.bankbackend.service.MerchantService;
import org.bankexample.bankbackend.service.TransactionService;
import org.bankexample.bankbackend.service.CardService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

import static org.bankexample.bankbackend.util.constants.PaymentConstants.PAYMENT_URL;

@RequiredArgsConstructor
@Service
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

        Payment payment = paymentRepository.save(paymentMapper.mapToPayment(dto));
        paymentRedirectUrlsService.save(payment.getId(), dto.getSuccessUrl(), dto.getFailedUrl(), dto.getErrorUrl());
        String paymentUrl = String.format("%s/%s", PAYMENT_URL, payment.getId());

        return paymentMapper.mapToPaymentCreatedResponseDTO(payment, paymentUrl, dto.getSuccessUrl(), dto.getFailedUrl(), dto.getErrorUrl());

    }

    @Override
    public PaymentResultResponseDTO initiatePayment(InitiatePaymentDTO dto) {

        Payment payment = paymentRepository.findById(UUID.fromString(dto.getPaymentId()))
                .orElseThrow(); // TODO Exception and Handler
        // Parameters validation TODO extract to separate service
        TransactionRequestDTO transactionRequestDTO = transactionMapper.mapInitiatePaymentDTOToTransactionRequestDTO(dto, payment);
        PaymentResultResponseDTO response = new PaymentResultResponseDTO();

        if (cardService.clientInSameBank(dto.getCardNumber())) {
            TransactionResultResponseDTO transactionResultDTO =  transactionService.holdFunds(transactionRequestDTO);
            // Send log to PCC, wait for confirmation
            response.setRedirectUrl(paymentRedirectUrlsService.getUrlForPaymentId(payment.getId(), transactionResultDTO.getTransactionResult()));
        }
        // Else
            // Generate acquirerOrderId, acquirerTimestamp
            // Send card data to PCC - forward request
            // Set redirect url String.format("redirect:%s", redirectUrl);

        return response;
    }


}
