package com.Roberto.crud.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.*;


@Entity
@Table(name = "user_model")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "statuses")
    private Status status = Status.PENDING;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonManagedReference
    private Set<Role> roles = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Wallet> wallets = new HashSet<>();

    public User(UUID id, String username, String password, Status status, Set<Role> roles, Set<Wallet> wallets) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.roles = roles;
        this.wallets = wallets;
    }

    public User() {
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Status getStatus() {
        return status;
    }

    public Set<Wallet> getWallets() {
        return wallets;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public void setWallets(Set<Wallet> wallets) {
        this.wallets = wallets;
    }
}
