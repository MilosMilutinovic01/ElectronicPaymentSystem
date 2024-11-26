package org.bankexample.bankbackend.repository;

import org.bankexample.bankbackend.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BankAccountRepository extends JpaRepository<BankAccount, UUID> {

    Optional<BankAccount> findBankAccountByAccountNumber(String bankAccountNumber);

}
