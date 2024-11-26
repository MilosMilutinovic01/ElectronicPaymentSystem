package org.bankexample.bankbackend.repository;

import org.bankexample.bankbackend.model.PaymentRedirectUrls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public interface PaymentRedirectUrlsRepository extends JpaRepository<PaymentRedirectUrls, UUID> {

    @Modifying
    @Transactional
    @Query("DELETE FROM PaymentRedirectUrls p WHERE p.createdAt < :cutoffDate")
    void deleteOlderThan(@Param("cutoffDate") Instant cutoffDate);

    Optional<PaymentRedirectUrls> findByPaymentId(UUID paymentId);

}
