package org.pccexample.pccbackend.controller;

import lombok.RequiredArgsConstructor;
import org.pccexample.pccbackend.dto.transaction.TransactionRequestDTO;
import org.pccexample.pccbackend.dto.transaction.TransactionResultResponseDTO;
import org.pccexample.pccbackend.service.PaymentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/payment")
@Validated
public class PaymentController {

    private final PaymentServiceImpl paymentService;

    @PostMapping("")
    public ResponseEntity<TransactionResultResponseDTO> forwardPaymentToIssuer(@RequestBody TransactionRequestDTO dto) {
        TransactionResultResponseDTO response = paymentService.forwardTransaction(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/ello")
    public String heyy() {
        return "Heyy";
    }
}
