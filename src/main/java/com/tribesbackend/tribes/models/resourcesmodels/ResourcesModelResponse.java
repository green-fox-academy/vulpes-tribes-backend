package com.tribesbackend.tribes.models.resourcesmodels;


public class ResourcesModelResponse {

    String type;
    int amount;
    int generation;

    public ResourcesModelResponse (String type, int amount, int generation) {
        this.type = type;
        this.amount = amount;
        this.generation =generation;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }
}
