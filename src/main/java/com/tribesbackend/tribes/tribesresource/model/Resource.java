package com.tribesbackend.tribes.tribesresource.model;

import com.tribesbackend.tribes.tribeskingdom.model.Kingdom;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


@Validated
@Entity
@Table(name = "resources")
public class Resource {
    @Id
    @GeneratedValue
    long resources_id;

    String type;
    @Min(value = 0L, message = "The value must be positive")
    @NotNull
    int amount;

    long timeStampLastVisit;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "kingdom_id", nullable = false)
    Kingdom kingdom;

    public Resource() {
    }

    public Resource(String type, Kingdom kingdom) {
        if (!type.equals("gold")||type.equals("food")){
            throw new IllegalArgumentException();
        }
        else this.type = type;

        switch(type){
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
        if (!type.equals("gold")||!type.equals("food")){
            throw new IllegalArgumentException();
        }
        else this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getTimeStampLastVisit() {
        return timeStampLastVisit;
    }

    public void setTimeStampLastVisit(long timeStampLastVisit) {
        this.timeStampLastVisit = timeStampLastVisit;
    }
}
