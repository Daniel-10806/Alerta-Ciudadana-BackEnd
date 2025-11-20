package com.alerta_ciudadana.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dni;
    private String fullName;
    private String passwordHash;
    private String role;

    public UserEntity() {}

    public UserEntity(String dni, String fullName, String passwordHash, String role) {
        this.dni = dni;
        this.fullName = fullName;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public Long getId() { return id; }
    public String getDni() { return dni; }
    public String getFullName() { return fullName; }
    public String getPasswordHash() { return passwordHash; }
    public String getRole() { return role; }
}