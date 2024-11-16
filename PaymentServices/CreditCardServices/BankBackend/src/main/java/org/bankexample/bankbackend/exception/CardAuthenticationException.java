package org.bankexample.bankbackend.exception;

public class CardAuthenticationException extends RuntimeException {

    public CardAuthenticationException() {
        super("Card authentication failed");
    }
}
