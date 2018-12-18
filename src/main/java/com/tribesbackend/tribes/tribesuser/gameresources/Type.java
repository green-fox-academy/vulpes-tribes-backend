package com.tribesbackend.tribes.tribesuser.gameresources;

public enum Type {
    gold(100),  //placeholder value, need to be changed according to real value of starting buildings
    food(0);

    int amount;

    Type(int amount) {
        this.amount = amount;
    }


    public void setAmount(int amount) {
        this.amount = amount;
    }
}
