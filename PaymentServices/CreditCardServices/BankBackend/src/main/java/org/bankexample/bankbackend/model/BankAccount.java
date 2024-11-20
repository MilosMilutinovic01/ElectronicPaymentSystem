package org.bankexample.bankbackend.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "bank_accounts")
@Data
@NoArgsConstructor
public class BankAccount {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true)
    private UUID id;

    @Column(name = "account_number", unique = true)
    private String accountNumber;

    @Column(name = "holder_name")
    private String holderName;

    @Column(name = "balance")
    private BigDecimal balance;

    @OneToMany
    @JoinColumn(name = "account_number", referencedColumnName = "account_number", insertable = false, updatable = false)
    private List<Card> cards;

}
