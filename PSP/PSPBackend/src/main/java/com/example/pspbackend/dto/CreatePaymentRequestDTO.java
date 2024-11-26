package com.example.pspbackend.dto;

import com.example.pspbackend.model.PaymentMethods;
import lombok.Value;

@Value
public class CreatePaymentRequestDTO {
    private PaymentMethods paymentMethod;
    private String redisId;

    private String merchantPassword;

    private String successUrl;
    private String failedUrl;
    private String errorUrl;
}
