package com.example.pspbackend.controller;

import com.example.pspbackend.dto.LoginRequestDTO;
import com.example.pspbackend.dto.RegistrationDTO;
import com.example.pspbackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth")
@Validated
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO userLogin) throws IllegalAccessException {
        return authService.login(userLogin);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegistrationDTO userRegister) {
        return authService.registration(userRegister);
    }
}