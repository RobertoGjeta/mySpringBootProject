package com.Roberto.crud.dto;

import com.Roberto.crud.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

public record LoginResponseDTO(String token,
                                       Date tokenExpirationTime,
                                       String refreshToken,
                                       Date refreshTokenExpirationTime,
                                       User user)
{
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String token;
        private Date tokenExpirationTime;
        private String refreshToken;
        private Date refreshTokenExpirationTime;
        private User user;

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder tokenExpirationTime(Date tokenExpirationTime) {
            this.tokenExpirationTime = tokenExpirationTime;
            return this;
        }

        public Builder refreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public Builder refreshTokenExpirationTime(Date refreshTokenExpirationTime) {
            this.refreshTokenExpirationTime = refreshTokenExpirationTime;
            return this;
        }

        public Builder User(User user) {
            this.user = user;
            return this;
        }

        public LoginResponseDTO build() {
            return new LoginResponseDTO(
                    token,
                    tokenExpirationTime,
                    refreshToken,
                    refreshTokenExpirationTime,
                    user
            );
        }
    }
}
