package org.pccexample.pccbackend.controller;

import lombok.RequiredArgsConstructor;
import org.pccexample.pccbackend.dto.transaction.TransactionResultResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/payment")
@Validated
public class PaymentController {

    @PostMapping
    public ResponseEntity<TransactionResultResponseDTO> forwardPaymentToIssuer(@RequestBody TransactionResultResponseDTO dto) {

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
