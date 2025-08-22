package com.Roberto.crud.dto;


import com.Roberto.crud.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public record LoginRequestDTO(String username,
                              String password) {
    public void setPassword(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        user.setPassword(encoder.encode(user.getPassword()));
    }

}
