package com.tribesbackend.tribes.models.jsonmodels;

import com.tribesbackend.tribes.models.Building;

import java.util.ArrayList;
import java.util.List;

public class BuildingModelListResponseJson {

    List<Building> buildings;

    public BuildingModelListResponseJson() {
    }

    public BuildingModelListResponseJson(List<Building> buildings) {
        this.buildings = buildings;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public BuildingModelListResponseJson setBuildings(List<Building> buildings) {
        this.buildings = buildings;
        return this;
    }
}
