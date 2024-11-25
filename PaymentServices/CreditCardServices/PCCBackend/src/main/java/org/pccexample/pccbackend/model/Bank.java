package org.pccexample.pccbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "banks")
@Data
@NoArgsConstructor
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true)
    private UUID id;

    @Column(name = "first_pan_numbers", unique = true)
    private String firstPanNumbers;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "url")
    private String url;

}
