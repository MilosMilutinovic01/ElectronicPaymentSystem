package com.example.pspbackend.repository;

import com.example.pspbackend.model.Client;
import com.example.pspbackend.model.PaymentMethods;
import com.example.pspbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface IPaymentMethodsRepository extends JpaRepository<PaymentMethods, UUID> {
    Optional<PaymentMethods> findByName(String name);
}
