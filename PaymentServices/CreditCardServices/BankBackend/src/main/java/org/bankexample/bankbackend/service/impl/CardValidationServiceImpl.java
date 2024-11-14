package org.bankexample.bankbackend.service.impl;

import org.bankexample.bankbackend.service.CardValidationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CardValidationServiceImpl implements CardValidationService {

    // card repository
    // bank account repository

    @Override
    public boolean validateCardAndFunds(String cardNumber, String expirationDate, String cvv,
                                        BigDecimal amount) {
        // Validate card details
        // Check available funds on bank account tied to credit card
        return true;
    }

    @Override
    public boolean clientInSameBank(String cardNumber) {
        return false;
    }

}
