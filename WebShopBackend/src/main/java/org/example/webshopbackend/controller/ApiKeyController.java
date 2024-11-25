package org.example.webshopbackend.controller;

import org.example.webshopbackend.dto.ApiKeyResponseDTO;
import org.example.webshopbackend.dto.BuyPackageResponseDTO;
import org.example.webshopbackend.dto.MessageDTO;
import org.example.webshopbackend.service.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/api-key")
public class ApiKeyController {
    @Autowired
    private ApiKeyService apiKeyService;

    @PreAuthorize("hasPermission('ADMIN')")
    @GetMapping
    public ResponseEntity<ApiKeyResponseDTO> getApiKey() {
        return apiKeyService.getApiKey();
    }

    @PreAuthorize("hasPermission('ADMIN')")
    @PostMapping
    public ResponseEntity<MessageDTO> setApiKey(@RequestBody String apiKey) {
        return apiKeyService.setApiKey(apiKey);
    }
}
