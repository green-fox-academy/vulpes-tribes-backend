package com.tribesbackend.tribes.models.buildingmodels;

import java.util.ArrayList;
import java.util.List;

public class BuildingModelListResponse {

    List<Building> buildings = new ArrayList<Building>();

    public BuildingModelListResponse(List<Building> buildings) {
        this.buildings = buildings;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }
}
