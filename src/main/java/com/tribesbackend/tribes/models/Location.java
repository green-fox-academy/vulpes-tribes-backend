package com.tribesbackend.tribes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "locations")
public class Location {
    @JsonIgnore
    @Id
    @GeneratedValue
    long id;
    int x;
    int y;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "kingdom_id", nullable = false)
    Kingdom kingdom;

    public Location() {
    }
    public Location(int x, int y, Kingdom kingdom) {
        this.x = x;
        this.y = y;
        this.kingdom = kingdom;
    }

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


}
