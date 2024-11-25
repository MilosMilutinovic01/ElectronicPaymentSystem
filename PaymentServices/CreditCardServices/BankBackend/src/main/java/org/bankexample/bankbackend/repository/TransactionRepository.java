package org.bankexample.bankbackend.repository;

import org.bankexample.bankbackend.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
