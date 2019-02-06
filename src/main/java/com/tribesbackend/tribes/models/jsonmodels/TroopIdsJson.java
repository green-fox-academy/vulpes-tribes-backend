package com.tribesbackend.tribes.models.jsonmodels;

import java.util.List;

public class TroopIdsJson {
    List<Long> troopIdsJson;

    public TroopIdsJson() {
    }

    public TroopIdsJson(List<Long> troopIdsJson) {
        this.troopIdsJson = troopIdsJson;
    }

    public List<Long> getTroopIdsJson() {
        return troopIdsJson;
    }

    public TroopIdsJson setTroopIdsJson(List<Long> troopIdsJson) {
        this.troopIdsJson = troopIdsJson;
        return this;
    }
}
