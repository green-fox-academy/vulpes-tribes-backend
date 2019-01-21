package com.tribesbackend.tribes.models;
import com.tribesbackend.tribes.models.buildingmodels.Building;

import com.tribesbackend.tribes.models.resourcesmodels.ResourcesModel;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Kingdom {

    @Id
    @GeneratedValue
    Long id;
    @NotNull
    @Size(min = 2, message = "Name of Kingdom should have at least 2 characters")
    @Column(name = "kingdomname",nullable = false, unique = true)
    public String name;
    public Kingdom(@NotNull @Size(min = 2, message = "Name of Kingdom should have at least 2 characters") String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TribesUser getTribesUser() {
        return tribesUser;
    }

    public void setTribesUser(TribesUser tribesUser) {
        this.tribesUser = tribesUser;
    }

    public List<Troop> getTroops() {
        return troops;
    }

    public void setTroops(List<Troop> troops) {
        this.troops = troops;
    }

    public Set<ResourcesModel> getResourcesModel() {
        return resourcesModel;
    }

    public void setResourcesModel(Set<ResourcesModel> resourcesModel) {
        this.resourcesModel = resourcesModel;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }

    @OneToOne(fetch = FetchType.LAZY, optional = false)

    @JoinColumn(name = "tribe_User_id", nullable = false)

    public TribesUser tribesUser;

    @OneToMany(mappedBy = "kingdom")
    private List<Troop> troops;

    @OneToMany(mappedBy = "kingdom")

    public Set<ResourcesModel> resourcesModel;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id", nullable = false)
    List<Building> buildings;

    public Kingdom(String name, TribesUser tribesUser) {

        this.name = name;
        this.tribesUser = tribesUser;
    }

    public Kingdom() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
