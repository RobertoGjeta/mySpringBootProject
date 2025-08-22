package com.Roberto.crud.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    @JsonBackReference
    private Set<User> users = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_privileges",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id"))
    @JsonManagedReference
    private Set<Privilege> privileges = new HashSet<>();

    public Role(UUID id, String roleName, Set<User> users, Set<Privilege> privileges) {
        this.id = id;
        this.roleName = roleName;
        this.privileges = privileges;
        this.users = users;
    }

    public Role() {
    }

    public UUID getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    public Set <Privilege>getPrivileges() {
        return privileges;
    }
    public Set<User> getUsers() {
        return users;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
