package org.pccexample.pccbackend.exception;

public class BankNotFoundByPanException extends RuntimeException {

    public BankNotFoundByPanException(String pan) {
        super(String.format("Bank not found by pan: %s", pan));
    }

}
