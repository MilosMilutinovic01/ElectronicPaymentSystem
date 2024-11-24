package org.bankexample.bankbackend.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginRequestDTO {
    @NotEmpty(message = "Username is required")
    private String username;
    @NotEmpty(message = "Password is required")
    private String password;
}
