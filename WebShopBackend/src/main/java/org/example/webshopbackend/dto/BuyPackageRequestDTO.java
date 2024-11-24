package org.example.webshopbackend.dto;

import lombok.Value;

@Value
public class BuyPackageRequestDTO {
    private Long packageId;
    private String username;
}
