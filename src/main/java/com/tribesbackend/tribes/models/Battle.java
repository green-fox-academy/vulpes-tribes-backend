package com.tribesbackend.tribes.models;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Battle {

    Kingdom defender;

    Kingdom attacker;

    List<Troop> defendersTroops;

    List<Troop> attackerTroops;

    public Battle(){

    }

    public Battle(Kingdom defender, Kingdom attacker, List<Troop> defendersTroops, List<Troop> attackerTroops) {
        this.defender = defender;
        this.attacker = attacker;
        this.defendersTroops = defendersTroops;
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

    public List<Troop> getDefendersTroops() {
        return defendersTroops;
    }

    public void setDefendersTroops(List<Troop> defendersTroops) {
        this.defendersTroops = defendersTroops;
    }

    public List<Troop> getAttackerTroops() {
        return attackerTroops;
    }

    public void setAttackerTroops(List<Troop> attackerTroops) {
        this.attackerTroops = attackerTroops;
    }
}
