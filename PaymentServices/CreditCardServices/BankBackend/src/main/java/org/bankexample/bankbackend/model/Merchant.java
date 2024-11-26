package org.bankexample.bankbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "merchants")
@Data
@NoArgsConstructor
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true)
    private UUID id;    // row id

    @Column(name = "merchant_name")
    private String merchantName;

    @Column(name = "merchant_id")
    private String merchantId;  // business logic id

    @Column(name = "password")
    private String password;    // api key

    @Column(name = "bank_account_number")
    private String bankAccountNumber;

}