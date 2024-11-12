package org.bankexample.bankbackend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ChargeRequestDTO {

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
