package org.bankexample.bankbackend.repository;

import org.bankexample.bankbackend.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
