package com.tribesbackend.tribes.tribesuser.gameresources;

import com.tribesbackend.tribes.tribesuser.model.TribesUser;

import javax.persistence.*;

@Entity
@Table(name = "GAME_RESOURCES")
public class Resources {
    @Id @GeneratedValue
    long resources_id;

    String type;
    int amount;
    int generation;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tribeUser_id", nullable = false)
    public TribesUser tribesUser;

    public Resources() {
    }

    public Resources(String type, int generation, TribesUser tribesUser) {
        if (!type.equals("gold")||type.equals("food")){
            throw new IllegalArgumentException();
        }
        else this.type = type;

        switch(type){
            case "gold":
                this.amount = 100;// to be specified
                break;
            case "food":
                this.amount = 0;
                break;
        }
        this.generation = generation;
        this.tribesUser = tribesUser;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (!type.equals("gold")||type.equals("food")){
            throw new IllegalArgumentException();
        }
        else this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }
}
