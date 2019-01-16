package com.tribesbackend.tribes.tribesresource.model;

import com.tribesbackend.tribes.tribeskingdom.model.Kingdom;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Validated
@Entity
@Table(name = "resources")
public class Resources {
    @Id
    @GeneratedValue
    long resources_id;
    @Min(value = 0L, message = "The value must be positive")
    @NotNull
    int amount;
    @Min(value = 0L, message = "The value must be positive")
    @NotNull
    int updated_at;
    String type;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "kingdom_id", nullable = false)
    Kingdom kingdom;

    public Resources() {
    }

    public Resources(String type,@Min(value = 0L, message = "The value must be positive") @NotNull int amount, @Min(value = 0L, message = "The value must be positive") @NotNull int updated_at, Kingdom kingdom) {
        if (type.equals("gold")||type.equals("food")){
            this.amount=amount;
            this.type = type;
            this.updated_at = updated_at;
            this.kingdom = kingdom;
        }
        else  throw new IllegalArgumentException();
    }

    public int getAmount() {
        return amount;
    }

    public int getUpdated_at() {
        return updated_at;
    }

    public String getType() {
        return type;
    }

    public static class ResourcesBuilder{
        @Id
        @GeneratedValue
        long resources_id;
        @Min(value = 0L, message = "The value must be positive")
        @NotNull
        int amount;
        @Min(value = 0L, message = "The value must be positive")
        int updated_at;
        String type;
        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "kingdom_id", nullable = false)
        Kingdom kingdom;

        public ResourcesBuilder() {
        }


        public Kingdom getKingdom() {
            return kingdom;
        }

        public ResourcesBuilder setResources_id(long resources_id) {
            this.resources_id = resources_id;
            return this;
        }

        public ResourcesBuilder setAmount(int amount) {
            this.amount = amount;
            return this;
        }

        public ResourcesBuilder setUpdated_at(int updated_at) {
            this.updated_at = updated_at;
            return this;
        }

        public ResourcesBuilder setType(String type) {
            this.type = type;
            return this;
        }

        public ResourcesBuilder setKingdom(Kingdom kingdom) {
            this.kingdom = kingdom;
            return this;
        }

        public Resources build() {
            return new Resources(type,amount, updated_at,kingdom);
        }
    }
}