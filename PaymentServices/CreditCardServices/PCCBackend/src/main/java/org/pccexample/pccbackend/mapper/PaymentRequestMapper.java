package org.pccexample.pccbackend.mapper;

import org.pccexample.pccbackend.dto.transaction.TransactionRequestDTO;
import org.pccexample.pccbackend.model.PaymentRequest;
import org.springframework.stereotype.Component;

@Component
public class PaymentRequestMapper {

    public PaymentRequest mapToPaymentRequest(TransactionRequestDTO dto) {
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setMerchantId(dto.getMerchantId());
        paymentRequest.setMerchantOrderId(dto.getMerchantOrderId());
        paymentRequest.setAcquirerOrderId(dto.getAcquirerOrderId());
        paymentRequest.setAcquirerTimestamp(dto.getAcquirerTimestamp());
        paymentRequest.setAmount(dto.getAmount());
        paymentRequest.setCardNumber(dto.getCardNumber());
        paymentRequest.setReceiverBankAccount(dto.getReceiverBankAccount());
        return paymentRequest;

    }

}
