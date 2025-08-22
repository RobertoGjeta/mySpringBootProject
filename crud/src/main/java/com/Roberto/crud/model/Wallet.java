package com.Roberto.crud.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "wallets")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "userId", nullable = false)
    private User user;
    private String currency;
    private int amount;

    public Wallet(UUID id, User user, String currency, int amount) {
        this.id = id;
        this.user = user;
        this.currency = currency;
        this.amount = amount;
    }

    public Wallet() {

    }

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getCurrency() {
        return currency;
    }

    public int getAmount() {
        return amount;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
