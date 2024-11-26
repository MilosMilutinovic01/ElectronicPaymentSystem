package com.example.pspbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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