package com.tribesbackend.tribes.models.jsonmodels;

public class KingdomInputJson {
    String name;
    int locationX;
    int locationY;

    public KingdomInputJson() {
    }
    public KingdomInputJson(String name, int locationX, int locationY) {
        this.name = name;
        this.locationX = locationX;
        this.locationY = locationY;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLocationX() {
        return locationX;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }


}
