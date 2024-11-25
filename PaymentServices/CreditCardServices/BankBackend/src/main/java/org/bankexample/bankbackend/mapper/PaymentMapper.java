package org.bankexample.bankbackend.mapper;

import org.bankexample.bankbackend.dto.payment.CreatePaymentDTO;
import org.bankexample.bankbackend.dto.payment.PaymentCreatedResponseDTO;
import org.bankexample.bankbackend.dto.payment.PaymentResultResponseDTO;
import org.bankexample.bankbackend.dto.transaction.TransactionResultResponseDTO;
import org.bankexample.bankbackend.model.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentCreatedResponseDTO mapToPaymentCreatedResponseDTO(Payment payment, String paymentUrl, String successUrl, String failedUrl, String errorUrl) {
        PaymentCreatedResponseDTO dto = new PaymentCreatedResponseDTO();
        dto.setPaymentId(String.valueOf(payment.getId()));
        dto.setPaymentUrl(paymentUrl);
        dto.setMerchantId(payment.getMerchantId());
        dto.setMerchantOrderId(payment.getMerchantOrderId());
        dto.setAmount(payment.getAmount());
//        dto.setSuccessUrl(successUrl);
//        dto.setFailedUrl(failedUrl);
//        dto.setErrorUrl(errorUrl);
        return dto;
    }

    public Payment mapToPayment(CreatePaymentDTO dto) {
        Payment payment = new Payment();
        payment.setMerchantId(dto.getMerchantId());
        payment.setMerchantOrderId(dto.getMerchantOrderId());
        payment.setMerchantTimestamp(dto.getMerchantTimestamp());
        payment.setAmount(dto.getAmount());
        return payment;
    }

    public Payment updateIssuerAcquirerFields(Payment payment, TransactionResultResponseDTO dto) {
        payment.setIssuerOrderId(dto.getIssuerOrderId());
        payment.setIssuerTimestamp(dto.getIssuerTimestamp());
        payment.setAcquirerOrderId(dto.getAcquirerOrderId());
        payment.setAcquirerTimestamp(dto.getAcquirerTimestamp());
        return payment;
    }

    public PaymentResultResponseDTO mapToPaymentResultResponseDTO(TransactionResultResponseDTO dto, String paymentId) {
        PaymentResultResponseDTO response = new PaymentResultResponseDTO();
        response.setPaymentId(paymentId);
        response.setTransactionResult(dto.getTransactionResult());
        response.setMerchantId(dto.getMerchantId());
        response.setMerchantOrderId(dto.getMerchantOrderId());
        response.setAcquirerOrderId(dto.getAcquirerOrderId());
        response.setAcquirerTimestamp(dto.getAcquirerTimestamp());
        response.setIssuerOrderId(dto.getIssuerOrderId());
        response.setIssuerTimestamp(dto.getIssuerTimestamp());
        response.setMessage(dto.getMessage());
        return response;

    }

}
