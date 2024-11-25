package com.example.pspbackend.controller;

import com.example.pspbackend.dto.OrderDataRequestDTO;
import com.example.pspbackend.dto.PaymentMethodsDTO;
import com.example.pspbackend.service.ClientService;
import com.example.pspbackend.service.PaymentMethodsService;
import com.example.pspbackend.service.interfaces.IClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/api/client")
public class ClientController {
    @Autowired
    private IClientService clientService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @PreAuthorize("hasPermission('CLIENT')")
    @PutMapping("/add-methods/{id}")
    public ResponseEntity addPaymentMethods(@PathVariable String id,
                                            @RequestBody Set<PaymentMethodsDTO> methods) {
        return clientService.addPaymentMethodsToClient(id, methods);
    }
    @PreAuthorize("hasPermission('CLIENT')")
    @GetMapping("/find-methods/{id}")
    public ResponseEntity findMethodsByClient(@PathVariable String id) {
        return clientService.findMethodsByClientId(id);
    }

    @GetMapping("/find-by-password/{password}")
    public ResponseEntity findMethodsByMerchantPassword(@PathVariable String password) {
        return clientService.findMethodsByMerchantPassword(password);
    }

    @PostMapping("/save-order-data")
    public ResponseEntity saveOrderData(@RequestBody OrderDataRequestDTO dto) {
        String redisId = UUID.randomUUID().toString(); // Generate a unique ID

        try {
            // Serialize DTO to JSON
            String jsonString = objectMapper.writeValueAsString(dto);

            // Save the JSON string to Redis
            stringRedisTemplate.opsForValue().set(redisId, jsonString, 3, TimeUnit.MINUTES); // Expire after 3 minutes

            // Optionally, you can store additional metadata
            stringRedisTemplate.opsForValue().set(redisId + ":status", "pending", 3, TimeUnit.MINUTES);

            // Return the Redis ID
            return ResponseEntity.ok(redisId);
        } catch (Exception e) {
            // Handle serialization error
            return ResponseEntity.status(500).body("Error saving order data");
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