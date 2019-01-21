package com.tribesbackend.tribes.models.jsonmodels;

public class RegistrationInputJson {
    String username;
    String password;
    String kingdomname;

    public RegistrationInputJson() {
    }

    public RegistrationInputJson(String username, String password, String kingdom) {
        this.username = username;
        this.password = password;
        this.kingdomname = kingdom;
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
        return kingdomname;
    }

    public void setKingdom(String kingdom) {
        this.kingdomname = kingdom;
    }
}
