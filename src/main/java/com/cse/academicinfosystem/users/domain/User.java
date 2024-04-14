package com.cse.academicinfosystem.users.domain;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;
}
