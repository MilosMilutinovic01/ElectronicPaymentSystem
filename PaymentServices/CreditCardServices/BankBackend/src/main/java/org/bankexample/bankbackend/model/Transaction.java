package org.bankexample.bankbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
public class Transaction {

    @Id
    private UUID id;
    @CreationTimestamp
    private Instant timestamp;
    private String merchantId;
    private String merchantOrderId;
//    private String merchantTimestamp;
    private BigDecimal amount;
    private String cardNumber;  // TODO mask
    private TransactionType transactionType;
    private TransactionResult transactionResult;
    private String acquirerOrderId;
    private String acquirerTimestamp;
    private String issuerOrderId;
    private String issuerTimestamp;
}
