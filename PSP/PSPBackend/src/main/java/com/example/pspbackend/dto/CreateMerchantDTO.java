package com.example.pspbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMerchantDTO {
    private String merchantName;
    private String merchantId;
    private String password;
    private String bankAccountNumber;
}
