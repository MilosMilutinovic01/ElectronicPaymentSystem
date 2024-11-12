package org.bankexample.bankbackend.dto;

import lombok.Data;

@Data
public class CreateMerchantDTO {

    private String merchantName;
    private String merchantId;
    private String password;

}
