package com.Roberto.crud.configuration;

import com.Roberto.crud.model.Privilege;
import com.Roberto.crud.model.Role;
import com.Roberto.crud.repository.PrivilegeRepository;
import com.Roberto.crud.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    private final RoleRepository roleRepository;

    private final PrivilegeRepository privilegeRepository;

    public SetupDataLoader(RoleRepository roleRepository,
                           PrivilegeRepository privilegeRepository) {
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup) {
            return;
        }
        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        Set<Privilege> adminPrivileges = Set.of(readPrivilege, writePrivilege);
        Set<Privilege> userPrivileges = Set.of(readPrivilege);
        createRoleIfNotFound("ADMIN", adminPrivileges);
        createRoleIfNotFound("USER", userPrivileges);

        alreadySetup = true;
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String privilegeName) {
        return privilegeRepository.findByPrivilegeName(privilegeName)
                .orElseGet(() -> privilegeRepository.save(new Privilege(null, privilegeName, new HashSet<>())));
    }

    @Transactional
    Role createRoleIfNotFound(String roleName, Set<Privilege> privileges) {

        return roleRepository.findByRoleName(roleName)
                .orElseGet(() -> roleRepository.save(new Role(null, roleName, new HashSet<>(), privileges)));
    }
}
