package org.bankexample.bankbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.bankexample.bankbackend.exception.BankAccountDoesNotExistException;
import org.bankexample.bankbackend.exception.InsufficientFundsInBankAccountException;
import org.bankexample.bankbackend.model.BankAccount;
import org.bankexample.bankbackend.model.Card;
import org.bankexample.bankbackend.repository.BankAccountRepository;
import org.bankexample.bankbackend.service.BankAccountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    @Override
    public void checkAvailableFunds(BigDecimal amount, String accountNumber) {

        BankAccount bankAccount = bankAccountRepository.findBankAccountByAccountNumber(accountNumber)
                .orElseThrow(() -> new BankAccountDoesNotExistException(accountNumber));

        if (bankAccount.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsInBankAccountException();
        }

    }


}
