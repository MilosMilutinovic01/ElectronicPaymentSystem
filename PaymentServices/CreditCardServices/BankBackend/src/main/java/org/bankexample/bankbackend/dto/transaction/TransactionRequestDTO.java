package org.bankexample.bankbackend.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
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
