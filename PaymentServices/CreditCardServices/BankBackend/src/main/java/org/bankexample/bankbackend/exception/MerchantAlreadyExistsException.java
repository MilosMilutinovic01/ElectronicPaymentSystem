package org.bankexample.bankbackend.exception;

public class MerchantAlreadyExistsException extends RuntimeException {
    public MerchantAlreadyExistsException(String id) {
        super(String.format("Merchant with id %s already exists", id));
    }
}
