package com.example.pspbackend.service.interfaces;

import com.example.pspbackend.dto.PaymentMethodsDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface IClientService {
    ResponseEntity addPaymentMethodsToClient(String clientId, Set<PaymentMethodsDTO> methods);
    ResponseEntity findMethodsByClient(String clientId);
}
