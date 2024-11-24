package org.bankexample.bankbackend.exception;

public class BankAccountDoesNotExistException extends RuntimeException {

    public BankAccountDoesNotExistException(String accountNumber) {
        super(String.format("Bank account with number %s does not exist", accountNumber));
    }
}
