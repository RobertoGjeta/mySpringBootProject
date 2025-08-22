package com.Roberto.crud.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "privileges")
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String privilegeName;

    @ManyToMany(mappedBy = "privileges")
    @JsonBackReference
    private Set<Role> roles = new HashSet<>();

    public Privilege(UUID id, String privilegeName, Set<Role> roles) {
        this.id = id;
        this.privilegeName = privilegeName;
        this.roles = roles;
    }

    public Privilege() {

    }

    public UUID getId() {
        return id;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
