package com.tribesbackend.tribes.models.jsonmodels;

public class KingdomInputJson {
    String name;
    int location_x;
    int location_y;

    public KingdomInputJson(String name, int location_x, int location_y) {
        this.name = name;
        this.location_x = location_x;
        this.location_y = location_y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLocation_x() {
        return location_x;
    }

    public void setLocation_x(int location_x) {
        this.location_x = location_x;
    }

    public int getLocation_y() {
        return location_y;
    }

    public void setLocation_y(int location_y) {
        this.location_y = location_y;
    }
}
