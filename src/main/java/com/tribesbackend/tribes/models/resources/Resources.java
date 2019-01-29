package com.tribesbackend.tribes.models.resources;

import java.util.List;

public class Resources {
    List<ResourcesModel> resources;

    public Resources() {
    }

    public Resources(List<ResourcesModel> resources) {
        this.resources = resources;
    }

    public List<ResourcesModel> getResources() {
        return resources;
    }

    public void setResources(List<ResourcesModel> resources) {
        this.resources = resources;
    }
}

