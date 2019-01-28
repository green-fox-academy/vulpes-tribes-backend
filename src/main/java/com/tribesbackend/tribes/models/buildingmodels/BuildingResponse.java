package com.tribesbackend.tribes.models.buildingmodels;


import java.util.ArrayList;
import java.util.List;


public class BuildingResponse {
    List<Building> buildingList = new ArrayList<Building>();

    public BuildingResponse(List<Building> buildingList) {
        this.buildingList = buildingList;
    }

    public BuildingResponse(){}

    public List<Building> getBuildingList() {
        return buildingList;
    }

    public void setBuildingList(List<Building> buildingList) {
        this.buildingList = buildingList;
    }
}
