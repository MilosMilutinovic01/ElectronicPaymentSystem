package org.bankexample.bankbackend.service;

import org.bankexample.bankbackend.model.BankAccount;
import org.bankexample.bankbackend.model.Card;

import java.math.BigDecimal;

public interface BankAccountService {

    void checkAvailableFunds(BigDecimal amount, String accountNumber);


}
