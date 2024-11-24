package org.pccexample.pccbackend.dto.transaction;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequestDTO {

    private BigDecimal amount;
    private String cardNumber;
    private String securityCode;
    private int expirationMonth;
    private int expirationYear;
    private String cardHolderName;

    private String receiverBankAccount;

    private String merchantId;
    private String merchantOrderId;
    private String acquirerOrderId;
    private String acquirerTimestamp;
}
