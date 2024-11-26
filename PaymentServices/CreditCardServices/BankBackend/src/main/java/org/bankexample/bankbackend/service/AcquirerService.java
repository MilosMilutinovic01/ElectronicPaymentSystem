package org.bankexample.bankbackend.service;

import org.bankexample.bankbackend.dto.payment.InitiatePaymentDTO;
import org.bankexample.bankbackend.dto.payment.CreatePaymentDTO;
import org.bankexample.bankbackend.dto.payment.PaymentCreatedResponseDTO;
import org.bankexample.bankbackend.dto.payment.PaymentResultResponseDTO;
import org.bankexample.bankbackend.dto.transaction.TransactionRequestDTO;
import org.bankexample.bankbackend.dto.transaction.TransactionResultResponseDTO;
import org.bankexample.bankbackend.model.Payment;

public interface AcquirerService {

    PaymentCreatedResponseDTO createPayment(CreatePaymentDTO dto);

    PaymentResultResponseDTO initiatePayment(InitiatePaymentDTO dto);

    void validateParametersForInitiatingPayment(InitiatePaymentDTO dto, Payment payment);

    TransactionResultResponseDTO forwardToPCC(TransactionRequestDTO dto);
}
