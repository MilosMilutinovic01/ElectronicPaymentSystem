package org.bankexample.bankbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bankexample.bankbackend.dto.CreateMerchantDTO;
import org.bankexample.bankbackend.dto.EditMerchantDTO;
import org.bankexample.bankbackend.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/merchant")
@Validated
public class MerchantController {

    private final MerchantService merchantService;

    @PostMapping
    ResponseEntity<Void> addMerchant(@Valid @RequestBody CreateMerchantDTO dto) {
        merchantService.addMerchant(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value="/{merchantId}")
    ResponseEntity<Void> editMerchant(@PathVariable String merchantId, @Valid @RequestBody EditMerchantDTO dto) {
        merchantService.editMerchant(merchantId, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
