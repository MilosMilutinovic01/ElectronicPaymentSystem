package org.pccexample.pccbackend.repository;

import org.pccexample.pccbackend.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

public interface BankRepository extends JpaRepository<Bank, UUID> {

    Optional<Bank> findByFirstPanNumbers(String pan);

}
