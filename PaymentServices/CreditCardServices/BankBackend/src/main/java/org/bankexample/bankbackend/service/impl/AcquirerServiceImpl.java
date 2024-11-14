package org.bankexample.bankbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.bankexample.bankbackend.dto.payment.InitiatePaymentDTO;
import org.bankexample.bankbackend.dto.payment.CreatePaymentDTO;
import org.bankexample.bankbackend.dto.payment.PaymentCreatedResponseDTO;
import org.bankexample.bankbackend.dto.payment.PaymentResultResponseDTO;
import org.bankexample.bankbackend.dto.transaction.TransactionRequestDTO;
import org.bankexample.bankbackend.dto.transaction.TransactionResultResponseDTO;
import org.bankexample.bankbackend.mapper.PaymentMapper;
import org.bankexample.bankbackend.model.Payment;
import org.bankexample.bankbackend.repository.PaymentRepository;
import org.bankexample.bankbackend.service.AcquirerService;
import org.bankexample.bankbackend.service.MerchantService;
import org.bankexample.bankbackend.service.TransactionService;
import org.bankexample.bankbackend.service.CardValidationService;
import org.springframework.stereotype.Service;

import static org.bankexample.bankbackend.util.constants.PaymentConstants.PAYMENT_URL;

@RequiredArgsConstructor
@Service
public class AcquirerServiceImpl implements AcquirerService {

    private final PaymentRepository paymentRepository;
    private final MerchantService merchantService;
    private final TransactionService transactionService;
    private final CardValidationService cardValidationService;

    @Override
    public PaymentCreatedResponseDTO createPayment(CreatePaymentDTO dto) {

        merchantService.authenticateMerchant(dto.getMerchantId(), dto.getMerchantPassword());

        Payment created = paymentRepository.save(PaymentMapper.INSTANCE.requestDtoToModel(dto));

        // TODO: move to mapper
        PaymentCreatedResponseDTO response = new PaymentCreatedResponseDTO(created.getId().toString(),
                String.format("%s/%s", PAYMENT_URL, created.getId()),
                created.getMerchantId(),
                created.getMerchantOrderId(),
                created.getAmount(),
                dto.getSuccessUrl(),
                dto.getFailedUrl(),
                dto.getErrorUrl());

        return response;
    }

    @Override
    public PaymentResultResponseDTO initiatePayment(InitiatePaymentDTO dto) {

        // Parameters validation TODO extract to separate service

        if (cardValidationService.clientInSameBank(dto.getCardNumber())) {
            TransactionResultResponseDTO resultResponseDTO =  transactionService.holdFunds(new TransactionRequestDTO());
            // Send log to PCC, wait for confirmation
        }
        // Else
            // Generate acquirerOrderId, acquirerTimestamp
            // Send card data to PCC - forward request

        // Set redirect url String.format("redirect:%s", redirectUrl);
        return new PaymentResultResponseDTO();
    }


}
