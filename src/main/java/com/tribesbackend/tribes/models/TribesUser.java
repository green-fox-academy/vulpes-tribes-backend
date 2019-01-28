package com.tribesbackend.tribes.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "users")
public class TribesUser implements UserDetails {
    @Id
    @GeneratedValue
    @JsonIgnore
    Long id;
    @NotNull
    @Size(min = 2, max = 45, message = "Username should have at least 2, maximum 45 characters")
    String username;
    @NotNull
    @Size(min = 2, message = "Password should have at least 2 characters")
    String password;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "kingdom_id", nullable = false)
    Kingdom kingdom;

    @Column(name = "logged_in")
    Boolean loggedIn = false;
    
    public TribesUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public TribesUser(String username, String password, Kingdom kingdom) {
        this.username = username;
        this.password = password;
        this.kingdom = kingdom;
    }

    public TribesUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Kingdom getKingdom() {
        return kingdom;
    }

    public void setKingdom(Kingdom kingdom) {
        this.kingdom = kingdom;
    }

    public Boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


    public static class TribesUserBuilder {
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

        @JoinColumn(name = "kingdom_id", nullable = false)
        @OneToOne(fetch = FetchType.LAZY, optional = false)
        Kingdom kingdom;

        @OneToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "resources_id", nullable = false)
        ResourcesModel resources;

        public TribesUserBuilder setTribesUser_id(long id) {
            this.id = id;
            return this;
        }

        public TribesUserBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public TribesUserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }
        public TribesUserBuilder setKingdom(String kingdomname) {
            this.kingdom = new Kingdom(kingdomname);
            return this;
        }

        public TribesUser build() {
            return new TribesUser(username, password);
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

        public String getPassword() {
            return password;
        }

        public Kingdom getKingdom() {
            return kingdom;
        }

        public void setKingdom(Kingdom kingdom) {
            this.kingdom = kingdom;
        }

        public ResourcesModel getResources() {
            return resources;
        }

        public void setResources(ResourcesModel resources) {
            this.resources = resources;
        }
    }
}
