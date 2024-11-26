package com.example.pspbackend.service.interfaces;

import com.example.pspbackend.dto.CreatePaymentRequestDTO;
import com.example.pspbackend.dto.OrderDataRequestDTO;
import com.example.pspbackend.model.PaymentMethods;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public interface IPaymentMethodsService {
    ResponseEntity findAll();
    ResponseEntity createPayment(CreatePaymentRequestDTO createPaymentRequestDTO);
}
