package org.pccexample.pccbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "payment_requests")
@Data
@NoArgsConstructor
public class PaymentRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true)
    private UUID id;
    private String merchantId;
    private String merchantOrderId;

    private BigDecimal amount;
    private String cardNumber;  // TODO mask
    private String receiverBankAccount;

    private String acquirerOrderId;
    private String acquirerTimestamp;

}
