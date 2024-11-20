package org.bankexample.bankbackend.exception;

public class PaymentParametersBadRequestException extends RuntimeException {

    public PaymentParametersBadRequestException(String message) {
        super(message);
    }

}
