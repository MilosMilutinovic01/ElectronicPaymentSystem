package org.example.webshopbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    public ResponseEntity getProducts() {
        return new ResponseEntity("\"Hello world!\"", HttpStatus.OK);
    }
}
