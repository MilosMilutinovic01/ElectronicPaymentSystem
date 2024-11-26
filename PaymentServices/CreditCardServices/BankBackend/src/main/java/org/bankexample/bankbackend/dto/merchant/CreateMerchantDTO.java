package org.bankexample.bankbackend.dto.merchant;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateMerchantDTO {

    private String merchantName;
    private String merchantId;
    private String password;
    private String bankAccountNumber;

}
