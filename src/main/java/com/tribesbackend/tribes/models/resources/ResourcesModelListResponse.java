package com.tribesbackend.tribes.models.resources;

import java.util.List;

public class ResourcesModelListResponse {
    List<ResourcesModel> resources;

    public ResourcesModelListResponse() {
    }

    public ResourcesModelListResponse(List<ResourcesModel> resources) {
        this.resources = resources;
    }

    public List<ResourcesModel> getResources() {
        return resources;
    }

    public void setResources(List<ResourcesModel> resources) {
        this.resources = resources;
    }
}

