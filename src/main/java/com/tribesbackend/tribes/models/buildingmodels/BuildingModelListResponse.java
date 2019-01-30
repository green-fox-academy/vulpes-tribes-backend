package com.tribesbackend.tribes.models.buildingmodels;

import java.util.ArrayList;
import java.util.List;

public class BuildingModelListResponse {

    List<Building> buildingList = new ArrayList<Building>();

    public BuildingModelListResponse() {
    }

    public BuildingModelListResponse(List<Building> buildingList) {
        this.buildingList = buildingList;
    }

    public List<Building> getBuildingList() {
        return buildingList;
    }

    public void  setBuildingList(List<Building> buildingList) {
        this.buildingList = buildingList;
    }
}