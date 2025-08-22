package com.Roberto.crud.service;

import com.Roberto.crud.model.User;
import com.Roberto.crud.model.UserPrincipal;
import com.Roberto.crud.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;


@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (!userRepository.existsByUsername(user.getUsername())) {
            throw new UsernameNotFoundException("User not found!");
        }

        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .flatMap(role -> role.getPrivileges().stream()
                        .map(privilege -> new SimpleGrantedAuthority(privilege.getPrivilegeName())))
                .collect(Collectors.toSet());

        authorities.addAll(user.getRoles().stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRoleName()))
                .collect(Collectors.toSet()));

        return new UserPrincipal(user,authorities);
    }
}
