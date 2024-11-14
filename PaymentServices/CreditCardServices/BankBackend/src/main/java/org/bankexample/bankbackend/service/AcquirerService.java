package org.bankexample.bankbackend.service;

import org.bankexample.bankbackend.dto.payment.InitiatePaymentDTO;
import org.bankexample.bankbackend.dto.payment.CreatePaymentDTO;
import org.bankexample.bankbackend.dto.payment.PaymentCreatedResponseDTO;
import org.bankexample.bankbackend.dto.payment.PaymentResultResponseDTO;

public interface AcquirerService {

    PaymentCreatedResponseDTO createPayment(CreatePaymentDTO dto);

    PaymentResultResponseDTO initiatePayment(InitiatePaymentDTO dto);

}
