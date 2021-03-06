package com.tribesbackend.tribes.tribesbuilding.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tribesbackend.tribes.tribeskingdom.model.Kingdom;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
public class Building {
    @Id
    @GeneratedValue
    @JsonIgnore
    Long id;
    @NotNull
    @Size(min = 2, message = "At least 2 charracters.")
    String type;
    @NotNull
    @Min(value = 0L, message = "The value must be positive" )
    int level;
    @NotNull
    @Min(value = 0L, message = "The value must be positive" )
    int HP;
    Timestamp started_at;
    Timestamp finished_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kingdom_id", nullable = false)
    private Kingdom kingdom;

    public Building() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public Timestamp getStarted_at() {
        return started_at;
    }

    public void setStarted_at(Timestamp started_at) {
        this.started_at = started_at;
    }

    public Timestamp getFinished_at() {
        return finished_at;
    }

    public void setFinished_at(Timestamp finished_at) {
        this.finished_at = finished_at;
    }

    public Kingdom getKingdom() {
        return kingdom;
    }

    public void setKingdom(Kingdom kingdom) {
        this.kingdom = kingdom;
    }

    public Building(@NotNull @Size(min = 2, message = "At least 2 charracters.") String type,
                    @NotNull @Min(value = 0L, message = "The value must be positive" ) int level,
                    @NotNull @Min(value = 0L, message = "The value must be positive" ) int HP) {
        this.type = type;
        this.level = level;
        this.HP = HP;
        this.kingdom = kingdom;
    }
}
