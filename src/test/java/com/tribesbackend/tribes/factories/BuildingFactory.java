package com.tribesbackend.tribes.factories;

import com.tribesbackend.tribes.models.buildingmodels.Building;

import java.util.ArrayList;
import java.util.List;

public class BuildingFactory {

    public static Building createSampleBuilding() {

        return new Building("farm", 1, 10);
    }

    public static List<Building> createInvalidBuildingList() {
        List<Building> invalidBuildings = new ArrayList<Building>();
        invalidBuildings.add(new Building("mine", -1, -12));
        invalidBuildings.add(new Building(null, 1, 12));
        invalidBuildings.add(new Building("", 1, 12));
        invalidBuildings.add(new Building("mine", 0, -12));
        return invalidBuildings;
    }
}
