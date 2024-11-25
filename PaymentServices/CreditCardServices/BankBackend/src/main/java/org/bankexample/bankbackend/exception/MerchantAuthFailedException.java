package org.bankexample.bankbackend.exception;

public class MerchantAuthFailedException extends RuntimeException {

    public MerchantAuthFailedException() {
        super("Merchant authentication failed");
    }

}
