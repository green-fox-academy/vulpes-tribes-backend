package com.tribesbackend.tribes.tribesuser.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tribesbackend.tribes.tribeskingdom.model.Kingdom;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class TribesUser {
    @Id
    @GeneratedValue
    @JsonIgnore
    Long id;
    @NotNull
    @Size(min = 2, max = 45, message = "Username should have at least 2, maximum 45 characters")
    String username;
    @NotNull
    @Size(min = 8, message = "Password should have at least 2 characters")
    String password;
    private String role;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "kingdom_id", nullable = false)
    Kingdom kingdom;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "resources_id", nullable = false)
    Kingdom resources;


    public TribesUser(@NotNull @Size(min = 2, max = 45, message = "Username should have at least 2, maximum 45 characters") String username, @NotNull @Size(min = 8, message = "Password should have at least 2 characters") String password) {
        this.username = username;
        this.password = password;
    }

    public TribesUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public TribesUser setRole(String role) {
        this.role = role;
        return this;
    }
}
