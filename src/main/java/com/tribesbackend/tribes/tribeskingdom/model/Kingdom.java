package com.tribesbackend.tribes.tribeskingdom.model;

import com.tribesbackend.tribes.tribesuser.model.TribesUser;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    public Kingdom(@NotNull @Size(min = 2, message = "Name of Kingdom should have at least 2 characters") String name, TribesUser tribesUser) {
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
