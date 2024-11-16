package org.bankexample.bankbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private UUID id;
    private String merchantId;
    private String merchantOrderId;
    private String merchantTimestamp;
    private BigDecimal amount;

}