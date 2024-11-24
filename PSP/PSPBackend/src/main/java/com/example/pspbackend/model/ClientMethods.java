package com.example.pspbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "client_methods")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientMethods {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @JsonIgnore
    private Client client;

    @ManyToOne
    @JoinColumn(name = "method_id", referencedColumnName = "id")
    @JsonIgnore
    private PaymentMethods method;
}
