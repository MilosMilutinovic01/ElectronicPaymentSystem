package org.bankexample.bankbackend.exception;

public class InsufficientFundsInBankAccountException extends RuntimeException {

    public InsufficientFundsInBankAccountException() {
        super("Insufficient Funds in Bank Account");
    }

}
