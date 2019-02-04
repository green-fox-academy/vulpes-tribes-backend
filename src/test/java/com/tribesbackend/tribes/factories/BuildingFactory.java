package com.tribesbackend.tribes.factories;

import com.tribesbackend.tribes.models.Building;

import java.util.ArrayList;
import java.util.List;

public class BuildingFactory {

    public static Building createSampleBuilding() {

        return new Building("farm", 1, 10);
    }

    public List<Building> giveMeSomeBuildingsList(){
        List<Building> validBuildings = new ArrayList<>();
        validBuildings.add(new Building("mine", 1, 12));
        validBuildings.add(new Building("farm",1, 12));
        validBuildings.add(new Building("mine", 2, 12));
        validBuildings.add(new Building("farm", 2, 12));
        validBuildings.add(new Building("barracks", 1, 12));
        return validBuildings;
    }

    public static List<Building> createInvalidBuildingList() {
        List<Building> invalidBuildings = new ArrayList<>();
        invalidBuildings.add(new Building("mine", -1, -12));
        invalidBuildings.add(new Building(null, 1, 12));
        invalidBuildings.add(new Building("", 1, 12));
        invalidBuildings.add(new Building("mine", 0, -12));
        return invalidBuildings;
    }
}
