package com.tribesbackend.tribes.models.jsonmodels;

import com.tribesbackend.tribes.models.Building;

import java.util.ArrayList;
import java.util.List;

public class BuildingModelListResponseJson {

    List<Building> buildingList = new ArrayList<Building>();

    public BuildingModelListResponseJson() {
    }

    public BuildingModelListResponseJson(List<Building> buildingList) {
        this.buildingList = buildingList;
    }

    public List<Building> getBuildingList() {
        return buildingList;
    }

    public void  setBuildingList(List<Building> buildingList) {
        this.buildingList = buildingList;
    }
}