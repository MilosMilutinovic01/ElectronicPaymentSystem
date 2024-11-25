package org.bankexample.bankbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true)
    private UUID id;
    private String merchantId;
    private String merchantOrderId;
    private String merchantTimestamp;

    private BigDecimal amount;
    private String cardNumber;  // TODO mask

    private String acquirerOrderId;
    private String acquirerTimestamp;
    private String issuerOrderId;
    private String issuerTimestamp;

    private boolean paymentAttempted;

}
