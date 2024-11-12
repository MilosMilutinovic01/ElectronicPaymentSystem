package org.bankexample.bankbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PaymentCreatedResponseDTO {

    private String paymentUrl;
    private String paymentId;
    private String merchantId;
    private String merchantOrderId;
    private BigDecimal amount;
    private String successUrl;
    private String failedUrl;
    private String errorUrl;
}
