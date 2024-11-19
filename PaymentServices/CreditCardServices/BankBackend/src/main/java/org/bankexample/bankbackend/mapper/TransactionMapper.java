package org.bankexample.bankbackend.mapper;

import org.bankexample.bankbackend.dto.payment.InitiatePaymentDTO;
import org.bankexample.bankbackend.dto.transaction.TransactionRequestDTO;
import org.bankexample.bankbackend.dto.transaction.TransactionResultResponseDTO;
import org.bankexample.bankbackend.model.Payment;
import org.bankexample.bankbackend.model.Transaction;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TransactionMapper {

    public Transaction mapTransactionRequestDTOToTransaction(TransactionRequestDTO dto) {
        Transaction transaction = new Transaction();
        transaction.setMerchantId(dto.getMerchantId());
        transaction.setMerchantOrderId(dto.getMerchantOrderId());
        transaction.setCardNumber(dto.getCardNumber());
        transaction.setAmount(dto.getAmount());
        transaction.setAcquirerOrderId(dto.getAcquirerOrderId());
        transaction.setAcquirerTimestamp(dto.getAcquirerTimestamp());
        return transaction;
    }

    public TransactionResultResponseDTO mapToTransactionResultResponseDTO(Transaction transaction) {
        TransactionResultResponseDTO dto = new TransactionResultResponseDTO();
        dto.setTransactionResult(transaction.getTransactionResult());
        dto.setMerchantId(dto.getMerchantId());
        dto.setMerchantOrderId(dto.getMerchantOrderId());
        dto.setAcquirerOrderId(dto.getAcquirerOrderId());
        dto.setAcquirerTimestamp(dto.getAcquirerTimestamp());
        dto.setIssuerOrderId(dto.getIssuerOrderId());
        dto.setIssuerTimestamp(dto.getIssuerTimestamp());
        return dto;
    }

    public TransactionRequestDTO mapInitiatePaymentDTOToTransactionRequestDTO(InitiatePaymentDTO dto, Payment payment) {
        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO();
        transactionRequestDTO.setCardNumber(dto.getCardNumber());
        transactionRequestDTO.setSecurityCode(dto.getCardSecurityCode());
        transactionRequestDTO.setExpirationMonth(dto.getExpirationMonth());
        transactionRequestDTO.setExpirationYear(dto.getExpirationYear());
        transactionRequestDTO.setCardHolderName(dto.getCardHolderName());
        transactionRequestDTO.setAmount(payment.getAmount());
        transactionRequestDTO.setMerchantId(payment.getMerchantId());
        transactionRequestDTO.setMerchantOrderId(payment.getMerchantOrderId());
        return transactionRequestDTO;
    }

}
