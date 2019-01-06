package com.tribesbackend.tribes.tribeskingdom.model;

<<<<<<< HEAD
import com.tribesbackend.tribes.tribesbuilding.model.Building;
=======
import com.tribesbackend.tribes.tribesresource.model.Resource;
>>>>>>> db0743486600504e737c32c908fb4d776ea8c337
import com.tribesbackend.tribes.tribesuser.model.TribesUser;
import com.tribesbackend.tribes.troop.model.Troop;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
<<<<<<< HEAD
=======
import java.util.Set;
>>>>>>> db0743486600504e737c32c908fb4d776ea8c337

@Entity
@Table
public class Kingdom {

    @Id
    @GeneratedValue
    Long id;

    @NotNull
    @Size(min = 2, message = "Name of Kingdom should have atleast 2 characters")
    @Column(nullable = false, unique = true)
    public String name;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tribeUser_id", nullable = false)
    private TribesUser tribesUser;
    @OneToMany(mappedBy = "kingdom")
    private List<Troop> troops;

    @OneToMany(mappedBy="kingdom")
    private Set<Resource> recource ;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id", nullable = false)
    List<Building> buildings;

    public Kingdom(String name) {
        this.name = name;
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
