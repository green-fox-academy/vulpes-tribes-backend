package com.tribesbackend.tribes.models;

import java.util.ArrayList;
import java.util.List;

public class TroopModelListResponse {
    private List<Troop> troops;

    public TroopModelListResponse(List<Troop> troops) {
        this.troops = troops;
    }

    public List<Troop> getTroops() {
        return troops;
    }

    public void setTroops(List<Troop> troops) {
        this.troops = troops;
    }
}