package com.tribesbackend.tribes.models.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tribesbackend.tribes.models.Kingdom;
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

    @Column(name = "updated_at")
    @JsonIgnore
    long updatedAt;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "kingdom"/*kingdom_id in table*/, nullable = false)
    Kingdom kingdom;

    public ResourcesModel() {
    }

    public ResourcesModel(String type, long amount, Kingdom kingdom) {
        this.type = type;
        this.amount = amount;
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

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
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
            return new ResourcesModel(type, amount, kingdom);
        }
    }
}

