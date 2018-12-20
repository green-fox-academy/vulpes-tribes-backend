package com.tribesbackend.tribes.tribeskingdom.model;

import com.tribesbackend.tribes.tribesresource.model.Resource;
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
