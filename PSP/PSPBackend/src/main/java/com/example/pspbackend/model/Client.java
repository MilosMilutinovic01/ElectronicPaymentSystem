package com.example.pspbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "clients")
@PrimaryKeyJoinColumn(name = "client_id")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Client extends User{
    @Column(name = "merchant_password")
    private String merchantPassword;

    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<ClientMethods> selectedMethods;
}
