package org.bankexample.bankbackend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreatePaymentRequestDTO {

    private String merchantId;
    private String merchantPassword;
    private BigDecimal amount;
    private String merchantOrderId;
    private String merchantTimestamp;
    private String successUrl;
    private String failedUrl;
    private String errorUrl;

}
