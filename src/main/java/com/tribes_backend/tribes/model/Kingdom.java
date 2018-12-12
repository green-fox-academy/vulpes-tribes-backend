package com.tribes_backend.tribes.model;


import javax.persistence.*;


@Entity
@Table
public class Kingdom {

    @Id
    @GeneratedValue
    Long id;
    String name;

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
