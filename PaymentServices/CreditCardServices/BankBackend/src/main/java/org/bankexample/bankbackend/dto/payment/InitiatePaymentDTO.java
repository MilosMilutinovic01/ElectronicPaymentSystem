package org.bankexample.bankbackend.dto.payment;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InitiatePaymentDTO {

    private String cardNumber;
    private String cardSecurityCode;
    private int expirationMonth;
    private int expirationYear;
    private String cardHolderName;
    private String paymentId;
    private String merchantId;

}
