package com.tribesbackend.tribes.models.jsonmodels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TroopSoloIdJson {

    @JsonProperty("level") public int level;

    public TroopSoloIdJson() {
    }

    public TroopSoloIdJson(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
