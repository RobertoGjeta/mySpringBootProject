package com.Roberto.crud.mappers;


import com.Roberto.crud.dto.LoginRequestDTO;
import com.Roberto.crud.dto.PrivilegeDTO;
import com.Roberto.crud.dto.RoleDTO;
import com.Roberto.crud.model.Privilege;
import com.Roberto.crud.model.Role;
import com.Roberto.crud.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", imports = {User.class, LoginRequestDTO.class})
public interface UserMapper {

    LoginRequestDTO toDTO(User user);
    User toModel(LoginRequestDTO loginRequestDTO);

    RoleDTO toDTO(Role role);
    Role toModel(RoleDTO roleDTO);

    PrivilegeDTO toDTO(Privilege privilege);
    Privilege toModel(PrivilegeDTO privilegeDTO);


}
