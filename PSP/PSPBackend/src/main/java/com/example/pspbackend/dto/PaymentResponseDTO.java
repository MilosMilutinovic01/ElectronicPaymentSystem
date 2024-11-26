package com.example.pspbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentResponseDTO {

    private String paymentId;
    private String paymentUrl;
    private String merchantId;
    private String merchantOrderId;
    private BigDecimal amount;
}
