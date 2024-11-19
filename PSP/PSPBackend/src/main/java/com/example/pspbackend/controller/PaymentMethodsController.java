package com.example.pspbackend.controller;

import com.example.pspbackend.model.PaymentMethods;
import com.example.pspbackend.service.PaymentMethodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/payment")
@Validated
public class PaymentMethodsController {
    @Autowired
    private PaymentMethodsService paymentMethodsService;
    @PreAuthorize("hasPermission('CLIENT')")
    @GetMapping("/")
    public ResponseEntity findAll() {
        return paymentMethodsService.findAll();
    }

}
