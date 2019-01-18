package com.tribesbackend.tribes.jsonmodels;

public class RegistrationInputJson {
    String username;
    String password;
    String kingdom;

    public RegistrationInputJson() {
    }

    public RegistrationInputJson(String username, String password, String kingdom) {
        this.username = username;
        this.password = password;
        this.kingdom = kingdom;
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

    public String getKingdom() {
        return kingdom;
    }

    public void setKingdom(String kingdom) {
        this.kingdom = kingdom;
    }
}
