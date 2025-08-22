package com.Roberto.crud.dto;

import com.Roberto.crud.model.Status;


public record UserDTO(
        String userName,
        String password,
        Status status,
        String roles) {

    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private String userName;
        private String password;
        private Status status;
        private String roles;

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }
        public Builder password(String password) {
            this.password = password;
            return this;
        }
        public Builder status(Status status) {
            this.status = status;
            return this;
        }
        public Builder roles(String roles) {
            this.roles = roles;
            return this;
        }
        public UserDTO build() {
            return new UserDTO(userName, password, status, roles);
        }
    }

}
