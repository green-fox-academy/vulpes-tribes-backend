package com.tribesbackend.tribes.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Battle {
    @Id
    @GeneratedValue
    long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "defender_id", nullable = false)
    Kingdom defender;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "attacker_id", nullable = false)
    Kingdom attacker;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "troops_battles",
            joinColumns = { @JoinColumn(name = "battle_id") },
            inverseJoinColumns = { @JoinColumn(name = "troop_id") })
    List<Troop> attackerTroops;

    public Battle(){
    }

    public Battle(Kingdom defender, Kingdom attacker, List<Troop> attackerTroops) {
        this.defender = defender;
        this.attacker = attacker;
        this.attackerTroops = attackerTroops;
    }

    public Kingdom getDefender() {
        return defender;
    }

    public void setDefender(Kingdom defender) {
        this.defender = defender;
    }

    public Kingdom getAttacker() {
        return attacker;
    }

    public void setAttacker(Kingdom attacker) {
        this.attacker = attacker;
    }

    public List<Troop> getAttackerTroops() {
        return attackerTroops;
    }

    public void setAttackerTroops(List<Troop> attackerTroops) {
        this.attackerTroops = attackerTroops;
    }
}

