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

    @Basic
    private java.sql.Timestamp sqlTimestamp;

    //int updated_at;//timestamp

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "kingdom_id", nullable = false)
    Kingdom kingdom;

    public Resource() {
    }

    public Resource(String type, Timestamp sqlTimestamp , Kingdom kingdom) {
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
        this.sqlTimestamp = sqlTimestamp;
        this.kingdom = kingdom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (!type.equals("gold")||type.equals("food")){
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

    public Timestamp getSqlTimestamp() {
        return sqlTimestamp;
    }

    public void setSqlTimestamp(Timestamp sqlTimestamp) {
        this.sqlTimestamp = sqlTimestamp;
    }
}
