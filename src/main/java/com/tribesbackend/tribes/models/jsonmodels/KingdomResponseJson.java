package com.tribesbackend.tribes.models.jsonmodels;

import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.Location;
import com.tribesbackend.tribes.models.Troop;
import com.tribesbackend.tribes.models.buildingmodels.Building;
import com.tribesbackend.tribes.models.resourcesmodels.ResourcesModel;

import java.util.List;

public class KingdomResponseJson {

    Long id;
    String name;
    long user_id;
    List<Building> buildings;
    List <ResourcesModel> resources;
    List <Troop> troops;
    Location location;

    public KingdomResponseJson(Kingdom kingdom ) {
        this.id = kingdom.getId();
        this.name = kingdom.getName();
        this.user_id = user_id;
        this.buildings = buildings;
        this.resources = resources;
        this.troops = troops;
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }

    public List<ResourcesModel> getResources() {
        return resources;
    }

    public void setResources(List<ResourcesModel> resources) {
        this.resources = resources;
    }

    public List<Troop> getTroops() {
        return troops;
    }

    public void setTroops(List<Troop> troops) {
        this.troops = troops;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
