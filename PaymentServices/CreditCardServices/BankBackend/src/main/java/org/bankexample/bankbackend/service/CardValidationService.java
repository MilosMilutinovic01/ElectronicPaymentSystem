package org.bankexample.bankbackend.service;

import java.math.BigDecimal;

public interface CardValidationService {

    boolean validateCardAndFunds(String cardNumber, String expirationDate, String cvv, BigDecimal amount);

    boolean clientInSameBank(String cardNumber);

}
