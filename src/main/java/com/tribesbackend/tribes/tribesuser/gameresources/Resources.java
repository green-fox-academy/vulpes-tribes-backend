package com.tribesbackend.tribes.tribesuser.gameresources;

public class Resources {
Type type;
int generation;

    public Resources() {
    }

    public Resources(Type type) {
        this.type = type;
        //placeholder value, need to be changed according to real value of starting buildings
        this.generation = 0;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    //manually generated setters for type setting
    public void setTypeGold (int goldAmount){
        Type.gold.setAmount(goldAmount);
    }

    public void setTypeFood (int foodAmount){
        Type.food.setAmount(foodAmount);
    }
}
