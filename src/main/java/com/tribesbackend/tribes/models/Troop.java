package com.tribesbackend.tribes.models;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "troops")
public class Troop {
    @Id
    @GeneratedValue
    private Long id;
    @Min(value = 0L, message = "The value must be positive")
    @NotNull
    private int level;
    @Min(value = 0L, message = "The value must be positive")
    @NotNull
    private int hp;
    @Min(value = 0L, message = "The value must be positive")
    @NotNull
    private int attack;
    @Min(value = 0L, message = "The value must be positive")
    @NotNull
    private int defence;
    private long startedAt;
    private long finishedAt;
    @ManyToOne
    @JoinColumn(name = "kingdom_id")
    private Kingdom kingdom;

    public Troop() {
    }

    public Troop(int level, int hp, int attack, int defence, long startedAt, long finishedAt) {
        this.level = level;
        this.hp = hp;
        this.attack = attack;
        this.defence = defence;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
    }

    public static class TroopBuilder {
        @Id
        @GeneratedValue
        private Long id;
        @Min(value = 0L, message = "The value must be positive")
        @NotNull
        private int level;
        @Min(value = 0L, message = "The value must be positive")
        @NotNull
        private int hp;
        @Min(value = 0L, message = "The value must be positive")
        @NotNull
        private int attack;
        @Min(value = 0L, message = "The value must be positive")
        @NotNull
        private int defence;
        private int started_at;
        private long finished_at;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "kingdom_id")
        private Kingdom kingdom;

        public TroopBuilder() {
        }

        public TroopBuilder setLevel(int level) {
            this.level = level;
            return this;
        }

        public TroopBuilder setHp(int hp) {
            this.hp = hp;
            return this;
        }

        public TroopBuilder setAttack(int attack) {
            this.attack = attack;
            return this;
        }

        public TroopBuilder setDefence(int defence) {
            this.defence = defence;
            return this;
        }

        public TroopBuilder setStarted_at(int started_at) {
            this.started_at = started_at;
            return this;
        }

        public TroopBuilder setFinished_at(long finished_at) {
            this.finished_at = finished_at;
            return this;
        }

        public Troop build() {
            return new Troop(level, hp, attack, defence, started_at, finished_at);
        }
    }
}
