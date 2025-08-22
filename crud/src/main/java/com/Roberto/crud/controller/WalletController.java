package com.Roberto.crud.controller;

import com.Roberto.crud.dto.WalletDTO;
import com.Roberto.crud.model.Wallet;
import com.Roberto.crud.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/createWallet")
    public ResponseEntity<String> registerWallet(@RequestBody WalletDTO walletDTO) {
        walletService.createWallet(walletDTO);
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/getMyWallets")
    public Set<Wallet> getMyWallets(@RequestParam String username) {
       return walletService.getPersonalWallets(username);
    }

    @GetMapping("/allWallets")
    public Iterable<Wallet> getAllWallets() {
        return walletService.getAllWallets();
    }
}
