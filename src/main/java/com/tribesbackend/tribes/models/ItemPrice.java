package com.tribesbackend.tribes.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "itemprice")
public class ItemPrice {
    @Id
    @GeneratedValue
    Long id;
    String type;
    long gold;

    public ItemPrice() {
    }

    public ItemPrice(String type, int gold) {
        this.type = type;
        this.gold = gold;
    }

    public Long getId() {
        return id;
    }

    public ItemPrice setId(Long id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public ItemPrice setType(String type) {
        this.type = type;
        return this;
    }

    public long getGold() {
        return gold;
    }

    public ItemPrice setGold(long gold) {
        this.gold = gold;
        return this;
    }
}
