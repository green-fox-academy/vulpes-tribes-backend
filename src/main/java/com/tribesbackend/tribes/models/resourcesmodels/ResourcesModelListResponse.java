package com.tribesbackend.tribes.models.resourcesmodels;

import java.util.ArrayList;
import java.util.List;

public class ResourcesModelListResponse {

    List<ResourcesModel> resources = new ArrayList<ResourcesModel>();

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
