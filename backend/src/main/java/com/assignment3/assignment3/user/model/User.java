package com.assignment3.assignment3.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String username;

    @Email
    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 120)
    private String password;
    @ManyToOne
    @JoinColumn(name = "role")
    @NotNull
    private Role role;

    @Column(nullable = false, length = 120)
    private String firstname;

    @Column(nullable = false, length = 120)
    private String lastname;
}
