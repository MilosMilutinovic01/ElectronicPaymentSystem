package com.example.pspbackend.service;

import com.example.pspbackend.dto.*;
import com.example.pspbackend.model.Client;
import com.example.pspbackend.repository.IClientRepository;
import com.example.pspbackend.repository.IPaymentMethodsRepository;
import com.example.pspbackend.service.interfaces.IPaymentMethodsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
public class PaymentMethodsService implements IPaymentMethodsService {

    @Autowired
    private IPaymentMethodsRepository paymentMethodsRepository;
    @Autowired
    private IClientRepository clientRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

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

    @Override
    public ResponseEntity createPayment(CreatePaymentRequestDTO createPaymentRequestDTO) {
        try {
            List<Client> allClients = clientRepository.findAll();

            Optional<Client> optionalClient = allClients.stream()
                    .filter(client -> encoder.matches(createPaymentRequestDTO.getMerchantPassword(), client.getMerchantPassword()))
                    .findFirst();

            if(optionalClient.isEmpty()){
                return ResponseEntity.badRequest()
                        .body(new MessageResponseDTO("Client does not exist!"));
            }

            OrderDataRequestDTO orderDataRequestDTO = getOrderData(createPaymentRequestDTO.getRedisId());

            CreatePaymentDTO paymentDTO = CreatePaymentDTO.builder()
                    .merchantId(optionalClient.get().getId().toString())
                    .merchantPassword(createPaymentRequestDTO.getMerchantPassword())
                    .amount(orderDataRequestDTO.getAmount())
                    .merchantOrderId(orderDataRequestDTO.getMerchantOrderId())
                    .merchantTimestamp(orderDataRequestDTO.getMerchantTimestamp())
                    .successUrl(createPaymentRequestDTO.getSuccessUrl())
                    .failedUrl(createPaymentRequestDTO.getFailedUrl())
                    .errorUrl(createPaymentRequestDTO.getErrorUrl())
                    .build();
            //AtomicReference<PaymentResponseDTO> paymentResponseDTO = new AtomicReference<>(new PaymentResponseDTO());
            PaymentResponseDTO paymentResponseDTO = WebClient.create()
                    .post()
                    .uri("http://localhost:8082/api/payment")
                    .bodyValue(paymentDTO)
                    .retrieve()
                    .bodyToMono(PaymentResponseDTO.class)
                    .block();
            return ResponseEntity.ok()
                    .body(paymentResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new MessageResponseDTO("Error while retrieving payment methods!"));
        }
    }

    private OrderDataRequestDTO getOrderData(String redisId) {
        try {
            // Retrieve the JSON string from Redis
            String jsonString = stringRedisTemplate.opsForValue().get(redisId);

            // Deserialize JSON string back to DTO
            OrderDataRequestDTO dto = objectMapper.readValue(jsonString, OrderDataRequestDTO.class);

            // Return the DTO
            return dto;
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
