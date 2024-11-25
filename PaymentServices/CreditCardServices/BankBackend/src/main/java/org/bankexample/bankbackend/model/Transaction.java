package org.bankexample.bankbackend.model;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true)
    private UUID id;
    @CreationTimestamp
    private Instant transactionTimestamp;
    private TransactionType transactionType;
    private TransactionResult transactionResult;

    private BigDecimal amount;
    private String cardNumber;  // TODO mask
    private String receiverBankAccountNumber;
    private String senderBankAccountNumber;

    private String merchantId;
    private String merchantOrderId;
//    private String merchantTimestamp;
    private String acquirerOrderId;
    private String acquirerTimestamp;
    private String issuerOrderId;
    private String issuerTimestamp;
}
