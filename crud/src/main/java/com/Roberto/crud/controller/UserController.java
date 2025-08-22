package com.Roberto.crud.controller;

import com.Roberto.crud.dto.LoginResponseDTO;
import com.Roberto.crud.dto.PageResponse;
import com.Roberto.crud.dto.UserDTO;
import com.Roberto.crud.dto.WalletDTO;
import com.Roberto.crud.model.User;
import com.Roberto.crud.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/myself")
    public User getparticularUser(@RequestParam String username) {
        return userService.findByUsername(username);
    }

    @GetMapping("/all")
    public PageResponse<User> getAll(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "5", required = false) int size)
    {
        Pageable pr = PageRequest.of(page, size, Sort.by("username").ascending());

        return userService.getUsers(pr);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
       userService.register(userDTO);
       return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestParam String username, @RequestParam String password) {
        return ResponseEntity.ok(userService.verify(username, password));
    }
}
