package org.bankexample.bankbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.bankexample.bankbackend.model.PaymentRedirectUrls;
import org.bankexample.bankbackend.model.TransactionResult;
import org.bankexample.bankbackend.repository.PaymentRedirectUrlsRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PaymentRedirectUrlsService {

    private final PaymentRedirectUrlsRepository repository;

    @Scheduled(fixedRate = 24 * 3600 * 1000) // Every 24h
    public void cleanupOldRedirectUrls() {
        LocalDateTime cutoffDate = LocalDateTime.now().minusDays(1);
        repository.deleteOlderThan(cutoffDate.toInstant(ZoneOffset.UTC));
        System.out.println("Old redirect URLs cleaned up at " + cutoffDate.toString());
    }

    public String getUrlForPaymentId(UUID paymentId, TransactionResult transactionResult) {
        if (transactionResult == null) {
            throw new IllegalArgumentException("Transaction result cannot be null");
        }
        PaymentRedirectUrls paymentRedirectUrls = repository.findByPaymentId(paymentId)
                .orElse(new PaymentRedirectUrls());
        return switch (transactionResult) {
            case SUCCESS -> paymentRedirectUrls.getSuccessUrl();
            case FAILURE -> paymentRedirectUrls.getFailedUrl();
            case ERROR -> paymentRedirectUrls.getErrorUrl();
        };
    }

    public PaymentRedirectUrls save(UUID paymentId, String successUrl, String failedUrl, String errorUrl) {
        return repository.save(new PaymentRedirectUrls(paymentId, successUrl, failedUrl, errorUrl));
    }

}
