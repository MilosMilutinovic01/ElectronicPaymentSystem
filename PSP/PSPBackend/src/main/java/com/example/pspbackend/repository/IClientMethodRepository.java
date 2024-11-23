package com.example.pspbackend.repository;

import com.example.pspbackend.model.Client;
import com.example.pspbackend.model.ClientMethods;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IClientMethodRepository extends JpaRepository<ClientMethods, UUID> {
    List<ClientMethods> findByClient(Client client);
    @Transactional
    void deleteAllByClient(Client client);
}
