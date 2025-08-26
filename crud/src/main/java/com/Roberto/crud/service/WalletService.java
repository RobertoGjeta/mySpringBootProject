package com.Roberto.crud.service;

import com.Roberto.crud.dto.WalletDTO;
import com.Roberto.crud.model.Role;
import com.Roberto.crud.model.User;
import com.Roberto.crud.model.Wallet;
import com.Roberto.crud.repository.RoleRepository;
import com.Roberto.crud.repository.UserRepository;
import com.Roberto.crud.repository.WalletRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JWTService jwtService;

    public WalletService(WalletRepository walletRepository, UserRepository userRepository, RoleRepository roleRepository, JWTService jwtService) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwtService = jwtService;
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

    public Set<Wallet> getPersonalWallets(UUID id, String token) {

        String username = jwtService.extractUserName(token);
        User jwtOwner = userRepository.findByUsername(username);

        if (jwtOwner == null) {
            throw new UsernameNotFoundException("User not found");
        }

        if(id != null) {
            boolean isAdmin = false;

            Role role = roleRepository.findByRoleName("ADMIN")
                    .orElseThrow(() -> new UsernameNotFoundException("Role not found"));


            for(Role r : roleRepository.findAll()) {
                if(jwtOwner.getRoles().contains(role)) {
                    isAdmin = true;
                    break;
                }
            }
            if(isAdmin) {
                jwtOwner = userRepository.findById(id)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        }

        return new HashSet<>(jwtOwner.getWallets());
    }

    public Iterable<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }
}
