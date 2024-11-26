package org.pccexample.pccbackend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pccexample.pccbackend.dto.transaction.TransactionRequestDTO;
import org.pccexample.pccbackend.dto.transaction.TransactionResultResponseDTO;
import org.pccexample.pccbackend.exception.BankNotFoundByPanException;
import org.pccexample.pccbackend.mapper.PaymentRequestMapper;
import org.pccexample.pccbackend.model.Bank;
import org.pccexample.pccbackend.model.PaymentRequest;
import org.pccexample.pccbackend.model.TransactionResult;
import org.pccexample.pccbackend.repository.BankRepository;
import org.pccexample.pccbackend.repository.PaymentRequestsRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
@Slf4j
public class PaymentServiceImpl {

    private final BankRepository bankRepository;
    private final PaymentRequestsRepository paymentRequestsRepository;
    private final PaymentRequestMapper paymentRequestMapper;

    public TransactionResultResponseDTO forwardTransaction(TransactionRequestDTO transactionRequestDTO) {

        PaymentRequest paymentRequest = paymentRequestMapper.mapToPaymentRequest(transactionRequestDTO);
        paymentRequestsRepository.saveAndFlush(paymentRequest);

        TransactionRequestDTO dto = transactionRequestDTO.toBuilder().build();
        try {
            String bankUrl = determineBankUrlFromPan(transactionRequestDTO.getCardNumber());
            String endpointUrl = String.format("%s/api/payment/hold", bankUrl);

            TransactionResultResponseDTO response = WebClient.create()
                    .post()
                    .uri(endpointUrl)
                    .bodyValue(dto)
                    .retrieve()
                    .bodyToMono(TransactionResultResponseDTO.class)
                    .onErrorResume(e -> {
                        log.error("Error occurred while calling Bank: {}", e.getMessage(), e);
                        String message = e.getMessage();
                        if (e.getMessage().startsWith("Connection refused")) {
                            message = "Connection to bank refused";
                        }
                        return Mono.just(new TransactionResultResponseDTO(
                                TransactionResult.ERROR,
                                transactionRequestDTO.getMerchantId(),
                                transactionRequestDTO.getMerchantOrderId(),
                                transactionRequestDTO.getAcquirerOrderId(),
                                transactionRequestDTO.getAcquirerTimestamp(),
                                message));
                    })
                    .block();
            if (response != null) {
                log.info(response.toString());
                return response;
            } else {
                throw new IllegalArgumentException("Response from bank is null.");
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            // TODO change message to generic
            return new TransactionResultResponseDTO(TransactionResult.ERROR, transactionRequestDTO.getMerchantId(), transactionRequestDTO.getMerchantOrderId(), transactionRequestDTO.getAcquirerOrderId(), transactionRequestDTO.getAcquirerTimestamp(), e.getMessage());
        }

    }

    public String determineBankUrlFromPan(String pan) {
        String firstPanNumbers = pan.substring(0, 6);
        Bank bank = bankRepository.findByFirstPanNumbers(firstPanNumbers)
                .orElseThrow();
        return bank.getUrl();
    }

}
