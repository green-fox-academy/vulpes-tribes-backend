package com.tribesbackend.tribes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Validated
@Entity
@Table(name = "resources")
public class ResourcesModel {
    @Id
    @GeneratedValue
    long id;
    String type;
    @Min(value = 0L, message = "The value must be positive")
    @NotNull
    long amount;
    @JsonIgnore
    Long updatedAt;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "kingdom_id", nullable = false)
    Kingdom kingdom;

    public ResourcesModel() {
    }
    public ResourcesModel(String type, long amount, Kingdom kingdom){
        this.type = type;
        this.amount = amount;
        this.kingdom = kingdom;
    }
//kingdom, type, amount, whenewer kingdom is save to db, related resources should be saved as well. In users controller.
    /*public ResourcesModel(String type, Kingdom kingdom) {
        if (type.equals("gold") || type.equals("food")) {
            this.type = type;
        } else throw new IllegalArgumentException();

        switch (type) {
            case "gold":
                this.amount = 100;// to be specified
                break;
            case "food":
                this.amount = 0;
                break;
        }
        this.kingdom = kingdom;
    }*/

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type.equals("gold") || type.equals("food")) {
            this.type = type;
        } else throw new IllegalArgumentException();
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getTimeStampLastVisit() {
        return updatedAt;
    }

    public void setTimeStampLastVisit(long timeStampLastVisit) {
        this.updatedAt = timeStampLastVisit;
    }

    public static class ResourcesBuilder {
        @Id
        @GeneratedValue
        long id;
        String type;
        @Min(value = 0L, message = "The value must be positive")
        @NotNull
        long amount;
        @JsonIgnore
        long timeStampLastVisit;
        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "kingdom_id", nullable = false)
        Kingdom kingdom;

        public ResourcesBuilder() {
        }

        public Kingdom getKingdom() {
            return kingdom;
        }

        public ResourcesModel.ResourcesBuilder setId(long id) {
            this.id = id;
            return this;
        }

        public ResourcesModel.ResourcesBuilder setType(String type) {
            this.type = type;
            return this;
        }

        public ResourcesModel.ResourcesBuilder setAmount(int amount) {
            this.amount = amount;
            return this;
        }

        public ResourcesModel.ResourcesBuilder setTimeStampLastVisit(int timeStampLastVisit) {
            this.timeStampLastVisit = timeStampLastVisit;
            return this;
        }

        public ResourcesModel.ResourcesBuilder setKingdom(Kingdom kingdom) {
            this.kingdom = kingdom;
            return this;
        }

        public ResourcesModel build() {
            return new ResourcesModel(type,amount, kingdom);
        }
    }
}

