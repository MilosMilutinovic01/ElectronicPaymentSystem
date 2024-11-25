package org.bankexample.bankbackend.controller;

import lombok.RequiredArgsConstructor;
import org.bankexample.bankbackend.dto.payment.InitiatePaymentDTO;
import org.bankexample.bankbackend.dto.payment.CreatePaymentDTO;
import org.bankexample.bankbackend.dto.payment.PaymentCreatedResponseDTO;
import org.bankexample.bankbackend.dto.payment.PaymentResultResponseDTO;
import org.bankexample.bankbackend.dto.transaction.TransactionRequestDTO;
import org.bankexample.bankbackend.dto.transaction.TransactionResultResponseDTO;
import org.bankexample.bankbackend.model.Transaction;
import org.bankexample.bankbackend.service.AcquirerService;
import org.bankexample.bankbackend.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/payment")
@Validated
public class PaymentController {

    private final AcquirerService acquirerService;
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<PaymentCreatedResponseDTO> createPaymentRequest(@RequestBody  CreatePaymentDTO dto) {
        PaymentCreatedResponseDTO created = acquirerService.createPayment(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PostMapping("/initiate")
    public ResponseEntity<PaymentResultResponseDTO> initiatePayment(@RequestBody InitiatePaymentDTO dto) {
        PaymentResultResponseDTO response = acquirerService.initiatePayment(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/hold")
    public ResponseEntity<TransactionResultResponseDTO> processHoldTransaction(@RequestBody TransactionRequestDTO dto) {
       return new ResponseEntity<>(transactionService.holdFunds(dto), HttpStatus.OK);
    }

    @GetMapping("/payment-success")
    public String paymentSuccess() {
        return "payment-success";  // Renders a view named 'payment-success.html'
    }


}
