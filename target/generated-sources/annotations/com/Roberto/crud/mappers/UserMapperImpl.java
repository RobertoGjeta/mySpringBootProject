package com.Roberto.crud.mappers;

import com.Roberto.crud.dto.LoginRequestDTO;
import com.Roberto.crud.dto.PrivilegeDTO;
import com.Roberto.crud.dto.RoleDTO;
import com.Roberto.crud.model.Privilege;
import com.Roberto.crud.model.Role;
import com.Roberto.crud.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-19T13:44:01+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public LoginRequestDTO toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        String password = null;
        String username = null;

        password = user.getPassword();
        username = user.getUsername();

        LoginRequestDTO loginRequestDTO = new LoginRequestDTO( username, password );

        return loginRequestDTO;
    }

    @Override
    public User toModel(LoginRequestDTO loginRequestDTO) {
        if ( loginRequestDTO == null ) {
            return null;
        }

        User user = new User();

        user.setPassword( loginRequestDTO.password() );
        user.setUsername( loginRequestDTO.username() );

        return user;
    }

    @Override
    public RoleDTO toDTO(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDTO.Builder roleDTO = RoleDTO.builder();

        return roleDTO.build();
    }

    @Override
    public Role toModel(RoleDTO roleDTO) {
        if ( roleDTO == null ) {
            return null;
        }

        Role role = new Role();

        return role;
    }

    @Override
    public PrivilegeDTO toDTO(Privilege privilege) {
        if ( privilege == null ) {
            return null;
        }

        PrivilegeDTO.Builder privilegeDTO = PrivilegeDTO.builder();

        return privilegeDTO.build();
    }

    @Override
    public Privilege toModel(PrivilegeDTO privilegeDTO) {
        if ( privilegeDTO == null ) {
            return null;
        }

        Privilege privilege = new Privilege();

        return privilege;
    }
}
