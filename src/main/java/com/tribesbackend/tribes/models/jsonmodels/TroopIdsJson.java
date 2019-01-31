package com.tribesbackend.tribes.models.jsonmodels;

import java.util.List;

public class TroopIdsJson {
    List<Long> troopIds;

    public TroopIdsJson() {
    }

    public TroopIdsJson(List<Long> troopIds) {
        this.troopIds = troopIds;
    }

    public List<Long> getTroopIds() {
        return troopIds;
    }

    public TroopIdsJson setTroopIds(List<Long> troopIds) {
        this.troopIds = troopIds;
        return this;
    }
}
