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
    int updated_at;
    String type;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "kingdom_id", nullable = false)
    Kingdom kingdom;

    public Resources() {
    }

    public Resources(String type, int updated_at, Kingdom kingdom) {
        if (type.equals("gold")||type.equals("food")){
            this.type = type;
            this.updated_at = updated_at;
            this.kingdom = kingdom;
        }
        else  throw new IllegalArgumentException();
    }

    public static class ResourceBuilder{
        @Id
        @GeneratedValue
        long resources_id;
        @Min(value = 0L, message = "The value must be positive")
        @NotNull
        int amount;
        int updated_at;
        String type;
        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "kingdom_id", nullable = false)
        Kingdom kingdom;

        public ResourceBuilder() {
        }

        public ResourceBuilder setResources_id(long resources_id) {
            this.resources_id = resources_id;
            return this;
        }

        public ResourceBuilder setAmount(int amount) {
            this.amount = amount;
            return this;
        }

        public ResourceBuilder setUpdated_at(int updated_at) {
            this.updated_at = updated_at;
            return this;
        }

        public ResourceBuilder setType(String type) {
            this.type = type;
            return this;
        }

        public ResourceBuilder setKingdom(Kingdom kingdom) {
            this.kingdom = kingdom;
            return this;
        }
    }
}
