package org.bankexample.bankbackend.service;

import org.bankexample.bankbackend.dto.ChargeRequestDTO;
import org.bankexample.bankbackend.dto.CreatePaymentRequestDTO;
import org.bankexample.bankbackend.dto.PaymentCreatedResponseDTO;

public interface AcquirerService {

    PaymentCreatedResponseDTO createPayment(CreatePaymentRequestDTO dto);

    String chargePayment(ChargeRequestDTO dto);

}
