package com.tribesbackend.tribes.models.jsonmodels;

import java.util.List;

public class PlayerListJson {
    List<RegistrationResponseJson> players;

    public PlayerListJson(List<RegistrationResponseJson> players) {
        this.players = players;
    }

    public List<RegistrationResponseJson> getPlayers() {
        return players;
    }

    public PlayerListJson setPlayers(List<RegistrationResponseJson> players) {
        this.players = players;
        return this;
    }
}
