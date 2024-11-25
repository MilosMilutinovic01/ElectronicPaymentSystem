package org.bankexample.bankbackend.exception;

public class MerchantDoesNotExistException extends RuntimeException {
    public MerchantDoesNotExistException(String id) {
        super(String.format("Merchant with id %s does not exists", id));
    }
}
