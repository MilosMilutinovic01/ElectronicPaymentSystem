package org.bankexample.bankbackend.dto.payment;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InitiatePaymentDTO {

    // Rename to initiate payment? -DONE

    private String cardNumber;
    private String cardSecurityCode;
    private int expirationMonth;
    private int expirationYear;
    private String cardHolderName;
    private String paymentId;
    private String merchantId;
//    private String successUrl;
//    private String failedUrl;
//    private String errorUrl;

}
