package com.tribesbackend.tribes.models.jsonmodels;

import com.tribesbackend.tribes.models.Troop;

import java.util.ArrayList;
import java.util.List;

public class TroopModelListResponseJson {
    private List<Troop> troops;

    public TroopModelListResponseJson(List<Troop> troops) {
        this.troops = troops;
    }

    public List<Troop> getTroops() {
        return troops;
    }

    public void setTroops(List<Troop> troops) {
        this.troops = troops;
    }
}