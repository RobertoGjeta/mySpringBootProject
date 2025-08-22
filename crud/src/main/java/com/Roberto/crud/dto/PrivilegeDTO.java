package com.Roberto.crud.dto;

public record PrivilegeDTO(String privilegeDTOName) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String privilegeDTOName;

        public Builder privilegeDTOName(String privilegeDTOName) {
            this.privilegeDTOName = privilegeDTOName;
            return this;
        }

        public PrivilegeDTO build() {
            return new PrivilegeDTO(privilegeDTOName);
        }
    }
}
