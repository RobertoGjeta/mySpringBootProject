package com.Roberto.crud.repository;

import com.Roberto.crud.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, UUID> {
    Optional<Privilege> findByPrivilegeName(String privilegeName);
}
