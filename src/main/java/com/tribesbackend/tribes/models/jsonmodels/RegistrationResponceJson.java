package com.tribesbackend.tribes.models.jsonmodels;

public class RegistrationResponceJson {
    Long id;
    String username;
    Long kingdom_id;
    String avatar;
    int points;

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

    public Long getKingdom_id() {
        return kingdom_id;
    }

    public void setKingdom_id(Long kingdom_id) {
        this.kingdom_id = kingdom_id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public RegistrationResponceJson(Long id, String username, Long kingdom_id, String avatar, int points) {
        this.id = id;
        this.username = username;
        this.kingdom_id = kingdom_id;
        this.avatar = avatar;
        this.points = points;
    }
}
