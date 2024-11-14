package org.bankexample.bankbackend.dto.transaction;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequestDTO {

    private String cardNumber;
    private String securityCode;
    private String expiryDate;
    private BigDecimal amount;
    private String merchantId;
    private String merchantOrderId;
    private String acquirerOrderId;
    private String acquirerTimestamp;
}
