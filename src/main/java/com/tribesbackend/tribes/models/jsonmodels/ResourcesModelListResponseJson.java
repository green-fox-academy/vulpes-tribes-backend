package com.tribesbackend.tribes.models.jsonmodels;

import com.tribesbackend.tribes.models.ResourcesModel;

import java.util.List;

public class ResourcesModelListResponseJson {
    List<ResourcesModel> resources;

    public ResourcesModelListResponseJson() {
    }

    public ResourcesModelListResponseJson(List<ResourcesModel> resources) {
        this.resources = resources;
    }

    public List<ResourcesModel> getResources() {
        return resources;
    }

    public void setResources(List<ResourcesModel> resources) {
        this.resources = resources;
    }
}

