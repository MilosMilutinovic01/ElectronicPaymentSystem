package com.example.pspbackend.service;

import com.example.pspbackend.dto.MessageResponseDTO;
import com.example.pspbackend.mapper.PaymentMethodsMapper;
import com.example.pspbackend.model.Client;
import com.example.pspbackend.model.PaymentMethods;
import com.example.pspbackend.repository.IClientRepository;
import com.example.pspbackend.repository.IPaymentMethodsRepository;
import com.example.pspbackend.service.interfaces.IPaymentMethodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class PaymentMethodsService implements IPaymentMethodsService {

    @Autowired
    private IPaymentMethodsRepository paymentMethodsRepository;

    public PaymentMethodsService(){}

    @Override
    public ResponseEntity findAll(){
        try {
            return ResponseEntity.ok()
                    .body(paymentMethodsRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new MessageResponseDTO("Error while retrieving payment methods!"));
        }
    }

}
