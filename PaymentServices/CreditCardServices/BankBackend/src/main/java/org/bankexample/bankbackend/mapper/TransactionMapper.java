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
        transaction.setReceiverBankAccountNumber(dto.getReceiverBankAccount());
        transaction.setCardNumber(dto.getCardNumber());
        transaction.setAmount(dto.getAmount());
        transaction.setAcquirerOrderId(dto.getAcquirerOrderId());
        transaction.setAcquirerTimestamp(dto.getAcquirerTimestamp());
        return transaction;
    }

    public TransactionResultResponseDTO mapToTransactionResultResponseDTO(Transaction transaction, String message) {
        TransactionResultResponseDTO dto = new TransactionResultResponseDTO();
        dto.setTransactionResult(transaction.getTransactionResult());
        dto.setMerchantId(transaction.getMerchantId());
        dto.setMerchantOrderId(transaction.getMerchantOrderId());
        dto.setAcquirerOrderId(transaction.getAcquirerOrderId());
        dto.setAcquirerTimestamp(transaction.getAcquirerTimestamp());
        dto.setIssuerOrderId(transaction.getIssuerOrderId());
        dto.setIssuerTimestamp(transaction.getIssuerTimestamp());
        dto.setMessage(message);
        return dto;
    }

    public TransactionRequestDTO mapInitiatePaymentDTOToTransactionRequestDTO(InitiatePaymentDTO dto, Payment payment, String receiverBankAccountNumber) {
        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO();
        transactionRequestDTO.setCardNumber(dto.getCardNumber());
        transactionRequestDTO.setSecurityCode(dto.getCardSecurityCode());
        transactionRequestDTO.setExpirationMonth(dto.getExpirationMonth());
        transactionRequestDTO.setExpirationYear(dto.getExpirationYear());
        transactionRequestDTO.setCardHolderName(dto.getCardHolderName());
        transactionRequestDTO.setReceiverBankAccount(receiverBankAccountNumber);
        transactionRequestDTO.setAmount(payment.getAmount());
        transactionRequestDTO.setMerchantId(payment.getMerchantId());
        transactionRequestDTO.setMerchantOrderId(payment.getMerchantOrderId());
        transactionRequestDTO.setAcquirerOrderId(payment.getAcquirerOrderId());
        transactionRequestDTO.setAcquirerTimestamp(payment.getAcquirerTimestamp());
        return transactionRequestDTO;
    }

}
