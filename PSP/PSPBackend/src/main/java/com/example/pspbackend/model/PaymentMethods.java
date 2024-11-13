package com.example.pspbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "payment_methods", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name"),
})
@Data
@NoArgsConstructor
public class PaymentMethods {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true)
    private UUID id;

    @Column(name = "name")
    private String name;
}
