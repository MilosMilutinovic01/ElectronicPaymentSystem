package org.bankexample.bankbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.bankexample.bankbackend.dto.ChargeRequestDTO;
import org.bankexample.bankbackend.dto.CreatePaymentRequestDTO;
import org.bankexample.bankbackend.dto.PaymentCreatedResponseDTO;
import org.bankexample.bankbackend.mapper.PaymentMapper;
import org.bankexample.bankbackend.model.Payment;
import org.bankexample.bankbackend.repository.PaymentRepository;
import org.bankexample.bankbackend.service.AcquirerService;
import org.bankexample.bankbackend.service.MerchantService;
import org.springframework.stereotype.Service;

import static org.bankexample.bankbackend.util.constants.PaymentConstants.PAYMENT_URL;

@RequiredArgsConstructor
@Service
public class AcquirerServiceImpl implements AcquirerService {

    private final PaymentRepository paymentRepository;
    private final MerchantService merchantService;

    @Override
    public PaymentCreatedResponseDTO createPayment(CreatePaymentRequestDTO dto) {

        // Validate merchant id and api key TODO extract method to separate service

        // Save payment to DB
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
    public String chargePayment(ChargeRequestDTO dto) {

        // Payment validation - id, merchantId, orderId TODO extract to separate service

        // Get merchant PAN from merchantService, Determine issuer bank from PAN

        // If client in same bank
            // Internal card validation
            // Do internal transaction of money - call method from separate service
            // Send log to PCC
        // Else
            // Send card data to PCC for validation and transaction

        // Set url
        // Send response to PSP
        return "";
    }


}
