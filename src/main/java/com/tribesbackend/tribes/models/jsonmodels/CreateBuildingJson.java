package com.tribesbackend.tribes.models.jsonmodels;

public class CreateBuildingJson {
    String type;

    public CreateBuildingJson() {
    }

    public CreateBuildingJson(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public CreateBuildingJson setType(String type) {
        this.type = type;
        return this;
    }
}
