package com.Roberto.crud.dto;

import java.util.UUID;

public class WalletDTO {
    private UUID userId;
    private String currency;
    private int amount;

    public WalletDTO(UUID userId, String currency, int amount) {
        this.userId = userId;
        this.currency = currency;
        this.amount = amount;
    }
    public UUID getUserId() {
        return userId;
    }
    public void setUserId(UUID userId) {
        this.userId = userId;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

}
