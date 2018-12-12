package com.tribes_backend.tribes.model;


import org.hibernate.annotations.GeneratorType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class TribesUser {
    @Id
    @GeneratedValue
    Long id;
    String username;
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

    public boolean checked (){

    }
}
