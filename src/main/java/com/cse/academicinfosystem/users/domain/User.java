package com.cse.academicinfosystem.users.domain;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_username", columnList = "username", unique = true)
})
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id = null;

    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
}
