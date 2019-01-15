package com.tribesbackend.tribes.tribesresources.model;

import com.tribesbackend.tribes.tribeskingdom.model.Kingdom;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Validated
@Entity
@Table(name = "resourcesModels")
public class ResourcesModel {
    @Id
    @GeneratedValue
    long resourcesId;
    String type;
    @Min(value = 0L, message = "The value must be positive")
    @NotNull
    long amount;
    long timeStampLastVisit;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "kingdom_id", nullable = false)
    Kingdom kingdom;

    public ResourcesModel() {
    }

    public ResourcesModel(String type, Kingdom kingdom) {
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
    }

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
        return timeStampLastVisit;
    }

    public void setTimeStampLastVisit(long timeStampLastVisit) {
        this.timeStampLastVisit = timeStampLastVisit;
    }

    public static class ResourcesBuilder {
        @Id
        @GeneratedValue
        long resourcesId;
        String type;
        @Min(value = 0L, message = "The value must be positive")
        @NotNull
        long amount;
        long timeStampLastVisit;
        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "kingdom_id", nullable = false)
        Kingdom kingdom;

        public ResourcesBuilder() {
        }

        public Kingdom getKingdom() {
            return kingdom;
        }

        public ResourcesModel.ResourcesBuilder setResourcesId(long resourcesId) {
            this.resourcesId = resourcesId;
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
            return new ResourcesModel(type, kingdom);
        }
    }
}
