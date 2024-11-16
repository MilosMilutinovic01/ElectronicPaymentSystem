package org.bankexample.bankbackend.service;

public interface CardService {

    void authenticateCard(String cardNumber, int expirationMonth, int expirationYear, String cvv);

    boolean clientInSameBank(String cardNumber);

    String getBankAccountNumber(String cardNumber);

}
