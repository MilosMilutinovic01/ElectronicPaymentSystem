package org.bankexample.bankbackend.dto.merchant;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EditMerchantDTO {
    private String merchantName;
    private String password;
    private String bankAccountNumber;
}
