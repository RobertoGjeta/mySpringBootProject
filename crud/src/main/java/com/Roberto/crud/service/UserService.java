package com.Roberto.crud.service;

import com.Roberto.crud.dto.*;
import com.Roberto.crud.mappers.UserMapper;
import com.Roberto.crud.model.*;
import com.Roberto.crud.repository.PrivilegeRepository;
import com.Roberto.crud.repository.RoleRepository;
import com.Roberto.crud.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PrivilegeRepository privilegeRepository;

    private final WalletService walletService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    private final JWTService jwtService;

    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PrivilegeRepository privilegeRepository, WalletService walletService, JWTService jwtService, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.walletService = walletService;
        this.jwtService = jwtService;
        this.userMapper = userMapper;
    }


    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public void register(UserDTO userDTO) {
        User user = new User();

        user.setUsername(userDTO.userName());
        user.setPassword(encoder.encode(userDTO.password()));
        user.setStatus(userDTO.status() == null ? Status.PENDING : userDTO.status());

        Role roleAdmin = roleRepository.findByRoleName("ADMIN")
                .orElseThrow(() -> new UsernameNotFoundException("Role not found"));

        Role roleUser = roleRepository.findByRoleName("USER")
                .orElseThrow(() -> new UsernameNotFoundException("Role not found"));

        Privilege readPrivilege = privilegeRepository.findByPrivilegeName("READ_PRIVILEGE")
                .orElseThrow(() -> new UsernameNotFoundException("Privilege not found"));

        Privilege writePrivilege = privilegeRepository.findByPrivilegeName("WRITE_PRIVILEGE")
                .orElseThrow(() -> new UsernameNotFoundException("Privilege not found"));

        Set<Privilege> adminPrivileges = Set.of(writePrivilege, readPrivilege);
        Set<Privilege> userPrivileges = Set.of(readPrivilege);

        roleAdmin.setPrivileges(adminPrivileges);
        roleUser.setPrivileges(userPrivileges);

        if(Objects.equals(userDTO.roles(), "ADMIN")){
            user.setRoles(Set.of(roleAdmin));
        }else if(Objects.equals(userDTO.roles(), "USER")){
            user.setRoles(Set.of(roleUser));
        }else{
            throw new UsernameNotFoundException("Role not found");
        }

         userRepository.save(user);
        Set<Wallet> wallets = Set.of(walletService.createWalletAutomatically(user));
        user.setWallets(wallets);
    }

    public LoginResponseDTO verify(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        LoginRequestDTO loginRequestDTO = userMapper.toDTO(user);
        loginRequestDTO.setPassword(user);

        if (!encoder.matches(password, loginRequestDTO.password())) {
            throw new BadCredentialsException("Invalid password");
        }

        String accessToken = jwtService.generateToken(loginRequestDTO.username());
        Date accessTokenExpiry = jwtService.extractExpiration(accessToken);

        String refreshToken = jwtService.generateRefreshToken(loginRequestDTO.username());
        Date refreshTokenExpiry = jwtService.extractExpiration(refreshToken);

        return LoginResponseDTO.builder()
                .token(accessToken)
                .tokenExpirationTime(accessTokenExpiry)
                .refreshToken(refreshToken)
                .refreshTokenExpirationTime(refreshTokenExpiry)
                .User(user)
                .build();
    }

    public PageResponse <User> getUsers(Pageable pageRequest) {
        Page<User> page = userRepository.findAll(pageRequest);
        return new PageResponse<>(page);
    }
}