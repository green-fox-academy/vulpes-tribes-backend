package com.tribes_backend.tribes.userModel;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table
public class TribesUser {
    @Id
    @GeneratedValue
    Long id;
    @NotNull
    @Size(min=2, message="Username should have atleast 2 characters")
    String username;
    @NotNull
    @Size(min=8, message="Password should have atleast 2 characters")
    String password;

    public TribesUser(String username) {
        this.username = username;
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

  public TribesUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public TribesUser() {
    }

}
