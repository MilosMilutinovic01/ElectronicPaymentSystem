package org.bankexample.bankbackend.repository;

import org.bankexample.bankbackend.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, UUID> {
    Optional<Merchant> findByMerchantId(String merchantId);
    boolean existsByMerchantId(String merchantId);
}
