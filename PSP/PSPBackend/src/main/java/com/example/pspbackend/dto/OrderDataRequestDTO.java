package com.example.pspbackend.dto;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class OrderDataRequestDTO {
    private BigDecimal amount;
    private String merchantOrderId;
    private String merchantTimestamp;
}
