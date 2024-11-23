package com.example.pspbackend.controller;

import com.example.pspbackend.dto.PaymentMethodsDTO;
import com.example.pspbackend.service.ClientService;
import com.example.pspbackend.service.PaymentMethodsService;
import com.example.pspbackend.service.interfaces.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/client")
public class ClientController {
    @Autowired
    private IClientService clientService;

    @PreAuthorize("hasPermission('CLIENT')")
    @PutMapping("/add-methods/{id}")
    public ResponseEntity addPaymentMethods(@PathVariable String id,
                                            @RequestBody Set<PaymentMethodsDTO> methods) {
        return clientService.addPaymentMethodsToClient(id, methods);
    }
    @PreAuthorize("hasPermission('CLIENT')")
    @GetMapping("/find-methods/{id}")
    public ResponseEntity findMethodsByClient(@PathVariable String id) {
        return clientService.findMethodsByClient(id);
    }

}
