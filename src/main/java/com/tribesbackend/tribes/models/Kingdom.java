package com.tribesbackend.tribes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tribesbackend.tribes.models.resources.ResourcesModel;
import com.tribesbackend.tribes.models.buildingmodels.Building;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity

@Table(name = "kingdoms")
public class Kingdom {

    @Id
    @GeneratedValue
    Long id;
    @NotNull
    @Size(min = 2, message = "Name of Kingdom should have at least 2 characters")
    @Column(name = "kingdomname", nullable = false, unique = true)
    public String kingdomname;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tribe_user_id", nullable = false)

    @JsonIgnore
    public TribesUser tribesUser;

    @OneToMany(mappedBy = "kingdom")
    private List <Troop> troops;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "kingdom" /*, orphanRemoval = true*/)
    public List <ResourcesModel> resourcesModel;

    @OneToOne(mappedBy = "kingdom", cascade = CascadeType.ALL)
    Location location;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "kingdom")
    List<Building> buildings;

    public Kingdom(String name) {
        this.kingdomname = name;
    }

    public Kingdom(String name, TribesUser tribesUser) {
        this.kingdomname = name;
        this.tribesUser = tribesUser;
        troops = new ArrayList<>();
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
