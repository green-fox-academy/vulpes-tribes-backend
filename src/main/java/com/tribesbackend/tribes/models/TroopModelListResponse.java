package com.tribesbackend.tribes.models;

import java.util.ArrayList;
import java.util.List;

public class TroopModelListResponse {
    List<Troop> troopList = new ArrayList<Troop>();

    public TroopModelListResponse(List<Troop> troopList) {
        this.troopList = troopList;
    }

    public TroopModelListResponse() {
    }

    public List<Troop> getTroopList() {
        return troopList;
    }

    public void setTroopList(List<Troop> troopList) {
        this.troopList = troopList;
    }
}