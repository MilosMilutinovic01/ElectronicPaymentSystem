package org.bankexample.bankbackend.exception;

import java.util.UUID;

public class PaymentDoesNotExistException extends RuntimeException {
    public PaymentDoesNotExistException() {
        super("Payment does not exist");
    }
}
