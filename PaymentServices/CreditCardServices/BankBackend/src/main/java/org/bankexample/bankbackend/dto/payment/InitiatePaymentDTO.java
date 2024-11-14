package org.bankexample.bankbackend.dto.payment;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InitiatePaymentDTO {

    // Rename to initiate payment? -DONE

    private String cardNumber;
    private String securityCode;
    private String expiryDate;
    private String paymentId;
    private String merchantId;
    private String merchantOrderId;
    private BigDecimal amount;
    private String successUrl;
    private String failedUrl;
    private String errorUrl;

}
