package com.example.pspbackend.dto;

import com.example.pspbackend.model.PaymentMethods;
import lombok.Value;
import org.yaml.snakeyaml.events.Event;

import java.math.BigDecimal;

@Value
public class OrderDataRequestDTO {
    private BigDecimal amount;
    private String merchantOrderId;
    private String merchantTimestamp;
}
