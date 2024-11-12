package org.bankexample.bankbackend.controller;

import lombok.RequiredArgsConstructor;
import org.bankexample.bankbackend.dto.ChargeRequestDTO;
import org.bankexample.bankbackend.dto.CreatePaymentRequestDTO;
import org.bankexample.bankbackend.dto.PaymentCreatedResponseDTO;
import org.bankexample.bankbackend.service.AcquirerService;
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

    @PostMapping
    public ResponseEntity<PaymentCreatedResponseDTO> createPaymentRequest(CreatePaymentRequestDTO dto) {
        PaymentCreatedResponseDTO created = acquirerService.createPayment(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PostMapping("/charge")
    public String chargePayment(@RequestBody ChargeRequestDTO dto) {
        String redirectUrl = acquirerService.chargePayment(dto);
        // Send response to PSP with url to redirect in WS to status page
        return String.format("redirect:%s", redirectUrl);
    }

    @GetMapping("/payment-success")
    public String paymentSuccess() {
        return "payment-success";  // Renders a view named 'payment-success.html'
    }


}
