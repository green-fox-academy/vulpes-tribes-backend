package com.tribesbackend.tribes.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tribesbackend.tribes.models.buildingmodels.Building;
import com.tribesbackend.tribes.models.resourcesmodels.ResourcesModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity

@Table(name = "KINGDOMS")
public class Kingdom {

    @Id
    @GeneratedValue
    Long id;
    @NotNull
    @Size(min = 2, message = "Name of Kingdom should have at least 2 characters")
    @Column(name = "kingdomname",nullable = false, unique = true)
    public String kingdomname;
<<<<<<< Updated upstream
=======
    @JsonIgnore
>>>>>>> Stashed changes
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tribe_User_id", nullable = false)
    public TribesUser tribesUser;
    @OneToMany(mappedBy = "kingdom")
    private List <Troop> troops;
    @OneToMany(mappedBy = "kingdom")
    public List <ResourcesModel> resourcesModel;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id", nullable = false)
    List<Building> buildings;


    public Kingdom(String name) {
        this.kingdomname = name;
    }

    public Kingdom(String name, TribesUser tribesUser) {
        this.kingdomname = name;
        this.tribesUser = tribesUser;
    }

    public Kingdom() {
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

    public List<ResourcesModel> getResourcesModel() {
        return resourcesModel;
    }

    public void setResourcesModel(List<ResourcesModel> resourcesModel) {
        this.resourcesModel = resourcesModel;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }

    public String getName() {
        return kingdomname;
    }

    public void setName(String name) {
        this.kingdomname = name;
    }
}
