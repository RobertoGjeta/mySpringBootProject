package com.Roberto.crud.service;

import com.Roberto.crud.dto.WalletDTO;
import com.Roberto.crud.model.User;
import com.Roberto.crud.model.Wallet;
import com.Roberto.crud.repository.UserRepository;
import com.Roberto.crud.repository.WalletRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    public WalletService(WalletRepository walletRepository, UserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
    }

    public Wallet createWallet(WalletDTO walletDTO) {
        Wallet wallet = new Wallet();
        wallet.setUser(userRepository.findById(walletDTO.getUserId()).
                orElseThrow(() -> new UsernameNotFoundException("User not found")));
        wallet.setCurrency(walletDTO.getCurrency() == null ? "LEK" : walletDTO.getCurrency());
        wallet.setAmount(walletDTO.getAmount());
        return walletRepository.save(wallet);
    }

    public Wallet createWalletAutomatically(User user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setCurrency("LEK");
        wallet.setAmount(0);
        return walletRepository.save(wallet);
    }

    public Set<Wallet> getPersonalWallets(String username){
        User user = userRepository.findByUsername(username);
        Set<Wallet> wallets = user.getWallets();
        return new HashSet<>(wallets);
    }

    public Iterable<Wallet> getAllWallets()
    {
        return walletRepository.findAll();
    }
}
