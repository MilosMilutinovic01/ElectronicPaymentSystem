package org.pccexample.pccbackend.repository;

import org.pccexample.pccbackend.model.PaymentRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRequestsRepository extends JpaRepository<PaymentRequest, Integer> {
}
