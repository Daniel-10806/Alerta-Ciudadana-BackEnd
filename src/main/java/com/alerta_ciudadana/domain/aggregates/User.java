package com.alerta_ciudadana.domain.aggregates;

import com.alerta_ciudadana.domain.valueobjects.Role;

public class User {

    private Long id;
    private String dni;
    private String fullName;
    private String passwordHash;
    private Role role;

    public User(String dni, String fullName, String passwordHash, Role role) {
        this.dni = dni;
        this.fullName = fullName;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public static User citizen(String dni, String fullName, String passwordHash) {
        return new User(dni, fullName, passwordHash, Role.CITIZEN);
    }

    public static User officer(String dni, String fullName, String passwordHash) {
        return new User(dni, fullName, passwordHash, Role.OFFICER);
    }

    public Long getId() { return id; }
    public String getDni() { return dni; }
    public String getFullName() { return fullName; }
    public String getPasswordHash() { return passwordHash; }
    public Role getRole() { return role; }
}