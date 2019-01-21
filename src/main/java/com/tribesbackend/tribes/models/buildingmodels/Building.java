package com.tribesbackend.tribes.models.buildingmodels;
import com.tribesbackend.tribes.models.Kingdom;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    long startedAt;
    long finishedAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kingdom_id", nullable = false)
    private Kingdom kingdom;

    public Building(String type) {
        this.type = type;
        level = 1;
        HP = 100;
        startedAt= new Timestamp(System.currentTimeMillis()).getNanos();
    }
    public Building(String type, int level, int HP) {
        this.type = type;
        this.level = level;
        this.HP = HP;
        this.kingdom = kingdom;

    }

    public Building() {}

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


    public long getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(long startedAt) {
        this.startedAt = startedAt;
    }

    public long getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(int finishedAt) {
        this.finishedAt = finishedAt;

    }

    public Kingdom getKingdom() {
        return kingdom;
    }

    public void setKingdom(Kingdom kingdom) {
        this.kingdom = kingdom;
    }


}
