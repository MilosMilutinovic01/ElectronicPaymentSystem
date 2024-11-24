package org.bankexample.bankbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
})
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private Role role;
}
