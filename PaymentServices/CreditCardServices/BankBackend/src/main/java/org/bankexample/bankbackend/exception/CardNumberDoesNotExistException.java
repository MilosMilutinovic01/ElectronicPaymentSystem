package org.bankexample.bankbackend.exception;

public class CardNumberDoesNotExistException extends RuntimeException {

    public CardNumberDoesNotExistException(String cardNumber) {
        super(String.format("Credit card with number %s does not exist", cardNumber.replaceAll("\\d(?=\\d{4})", "*")));
    }

}
