package com.tribes_backend.tribes.tribesKingdom.model;

import com.tribes_backend.tribes.tribesUser.model.TribesUser;

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
    @Size(min = 2, message = "Name of Kingdom should have atleast 2 characters")
    public String name;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tribeUser_id", nullable = false)
    private TribesUser tribesUser;

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
