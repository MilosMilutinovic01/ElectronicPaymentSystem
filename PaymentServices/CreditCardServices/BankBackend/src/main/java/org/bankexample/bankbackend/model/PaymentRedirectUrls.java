package org.bankexample.bankbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.bankexample.bankbackend.util.constants.PaymentConstants.*;

@Entity
@Data
@AllArgsConstructor
public class PaymentRedirectUrls {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true)
    private UUID id;
    @Column(name = "payment_id")
    private UUID paymentId;
    @Column(name = "success_url")
    private String successUrl;
    @Column(name = "failed_url")
    private String failedUrl;
    @Column(name = "error_url")
    private String errorUrl;
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    public PaymentRedirectUrls(UUID paymentId, String successUrl, String failedUrl, String errorUrl) {
        this.paymentId = paymentId;
        this.successUrl = successUrl;
        this.failedUrl = failedUrl;
        this.errorUrl = errorUrl;
    }

    public PaymentRedirectUrls() {  // Default payment redirects to this bank, if originally provided are not found
        this.successUrl = PAYMENT_SUCCESS_URL;
        this.failedUrl = PAYMENT_FAILED_URL;
        this.errorUrl = PAYMENT_ERROR_URL;
    }
}
