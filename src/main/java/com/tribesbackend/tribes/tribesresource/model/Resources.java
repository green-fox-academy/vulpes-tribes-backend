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
    long updated_at;
    String type;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "kingdom_id", nullable = false)
    Kingdom kingdom;

    public Resources() {
    }

    public Resources(@Min(value = 0L, message = "The value must be positive") @NotNull int amount, long updated_at,String type) {
        if (!type.equals("gold")||!type.equals("food")){
            throw new IllegalArgumentException();
        }
        else this.type = type;

        switch(type){
            case "gold":
                this.amount = 0;
                break;
            case "food":
                this.amount = 0;
                break;
        }
        this.updated_at = updated_at;
    }

    public int getAmount() {
        return amount;
    }

    public long getUpdated_at() {
        return updated_at;
    }

    public String getType() {
        return type;
    }

    public static class ResourcesBuilder {
        @Id
        @GeneratedValue
        long resources_id;
        @Min(value = 0L, message = "The value must be positive")
        @NotNull
        int amount;
        long updated_at;
        String type;
        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "kingdom_id", nullable = false)
        Kingdom kingdom;

        public ResourcesBuilder(){
        }

        public ResourcesBuilder setAmount(int amount) {
            this.amount = amount;
            return this;
        }

        public ResourcesBuilder setUpdated_at(long updated_at) {
            this.updated_at = updated_at;
            return this;
        }

        public ResourcesBuilder setType(String type) {
            if (!type.equals("gold")||!type.equals("food")){
                throw new IllegalArgumentException();
            }
            else this.type = type;
            return this;
        }

        public int getAmount() {
            return amount;
        }

        public long getUpdated_at() {
            return updated_at;
        }

        public String getType() {
            return type;
        }

        public long getResources_id() {
            return resources_id;
        }

        public Kingdom getKingdom() {
            return kingdom;
        }

        public Resources build() {
            return new Resources(amount,updated_at,type);
        }
    }
}
