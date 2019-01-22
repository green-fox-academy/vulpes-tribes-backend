package com.tribesbackend.tribes.models.resourcesmodels;

import java.util.ArrayList;
import java.util.List;

public class ResourcesModelBuilder {

    List<ResourcesModelConstructor> resources = new ArrayList<ResourcesModelConstructor>();

    public ResourcesModelBuilder(List<ResourcesModelConstructor> resources) {
        this.resources = resources;
    }

    public void setResources(List<ResourcesModelConstructor> resources) {
        this.resources = resources;
    }
}
