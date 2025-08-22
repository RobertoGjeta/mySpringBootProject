package com.Roberto.crud.dto;

import java.util.Set;

public record RoleDTO(String roleDTOName/*,
                      Set<PrivilegeDTO> privileges*/) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String roleDTOName;
//        private Set<PrivilegeDTO> privileges;

        public Builder roleDTOName(String roleDTOName) {
            this.roleDTOName = roleDTOName;
            return this;
        }

//        public Builder privileges(Set<PrivilegeDTO> privileges) {
//            this.privileges = privileges;
//            return this;
//        }

        public RoleDTO build() {
            return new RoleDTO(
                    roleDTOName
//                    privileges
            );
        }
    }
}
