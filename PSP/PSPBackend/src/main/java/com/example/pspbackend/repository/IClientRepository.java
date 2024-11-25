package com.example.pspbackend.repository;

import com.example.pspbackend.model.Client;
import com.example.pspbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IClientRepository extends JpaRepository<Client, UUID> {
    Optional<Client> findByUsername(String username);
    Optional<Client> findByMerchantPassword(String merchantPassword);
}
