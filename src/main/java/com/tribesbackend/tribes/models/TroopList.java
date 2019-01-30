package com.tribesbackend.tribes.models;

import java.util.List;

public class TroopList {
    List<Troop> troops;

    public TroopList(List<Troop> troops) {
        this.troops = troops;
    }

    public List<Troop> getTroops() {
        return troops;
    }

    public TroopList setTroops(List<Troop> troops) {
        this.troops = troops;
        return this;
    }
}
