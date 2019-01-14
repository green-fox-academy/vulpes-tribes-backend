package com.tribesbackend.tribes.tribeskingdom.model;


import com.tribesbackend.tribes.tribesbuilding.model.Building;

import com.tribesbackend.tribes.tribesresource.model.ResourcesModel;

import com.tribesbackend.tribes.tribesuser.model.TribesUser;
import com.tribesbackend.tribes.troop.model.Troop;

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
    @Column(nullable = false, unique = true)
    public String name;

    @OneToOne (fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tribeUser_id", nullable = false)

    public TribesUser tribesUser;

    @OneToMany(mappedBy = "kingdom")
    private List<Troop> troops;

    @OneToMany(mappedBy = "kingdom")
    public Set<ResourcesModel> resourcesModels;

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
