package com.Roberto.crud.repository;

import com.Roberto.crud.model.Wallet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WalletRepository extends CrudRepository<Wallet, UUID> {
    Wallet findByAmount(int amount);
}
