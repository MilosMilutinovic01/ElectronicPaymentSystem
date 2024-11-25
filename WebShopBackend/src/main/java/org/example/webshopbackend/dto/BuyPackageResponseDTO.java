package org.example.webshopbackend.dto;

import lombok.Value;

@Value
public class BuyPackageResponseDTO {
    private String apiKey;
    private String redisId;
}
