package com.tribesbackend.tribes.tribesbuilding.model;


import java.util.ArrayList;
import java.util.List;


public class Buildings {
    List<Building> buildingList = new ArrayList<Building>();

    public Buildings(List<Building> buildingList) {
        this.buildingList = buildingList;
    }

    public Buildings() {
    }

    public List<Building> getBuildingList() {
        return buildingList;
    }

    public void setBuildingList(List<Building> buildingList) {
        this.buildingList = buildingList;
    }
}
