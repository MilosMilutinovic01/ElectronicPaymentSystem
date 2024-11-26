package com.example.pspbackend.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO {
    @NotEmpty(message = "Name is required")
    private String name;
    @NotEmpty(message = "Username is required")
    private String username;
    @NotEmpty(message = "Password is required")
    private String password;
    @NotEmpty(message = "Bank is required")
    private String bank;
    @NotEmpty(message = "Bank account number is required")
    private String bankAccount;
    @NotEmpty(message = "Payment methods are required")
    private Set<PaymentMethodsDTO> paymentMethods;
}
