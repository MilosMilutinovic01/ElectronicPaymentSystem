package org.bankexample.bankbackend.dto.payment;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreatePaymentDTO {

    private String merchantId;
    private String merchantPassword;

    private BigDecimal amount;
    private String merchantOrderId;
    private String merchantTimestamp;

    private String successUrl;
    private String failedUrl;
    private String errorUrl;

}
