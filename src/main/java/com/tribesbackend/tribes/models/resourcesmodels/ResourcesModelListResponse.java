package com.tribesbackend.tribes.models.resourcesmodels;

import java.util.ArrayList;
import java.util.List;

public class ResourcesModelListResponse {

    List<ResourcesModelResponse> resources = new ArrayList<ResourcesModelResponse>();

    public ResourcesModelListResponse(List<ResourcesModelResponse> resources) {
        this.resources = resources;
    }

    public void setResources(List<ResourcesModelResponse> resources) {
        this.resources = resources;
    }

    public List<ResourcesModelResponse> getResources() {
        return resources;
    }
}
