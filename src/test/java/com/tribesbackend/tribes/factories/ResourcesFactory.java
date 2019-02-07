package com.tribesbackend.tribes.factories;

import com.tribesbackend.tribes.models.ResourcesModel;

import java.util.ArrayList;
import java.util.List;

public class ResourcesFactory {
    public  List<ResourcesModel> createValidSampleResources() {
        List<ResourcesModel> rmList = new ArrayList<>();
        rmList.add(validGoldModel());
        rmList.add(validFoodModel());
        return rmList;


    }
    public ResourcesModel validGoldModel(){
        ResourcesModel gold = new ResourcesModel();
        gold.setType("gold");
        gold.setAmount(380);
        gold.setUpdatedAt(1549256400000L);
        gold.setGenerated(0);
        return gold;
    }

    public ResourcesModel validFoodModel(){
        ResourcesModel food = new ResourcesModel();
        food.setType("food");
        food.setAmount(0);
        food.setUpdatedAt(1549256400000L);
        food.setGenerated(0);
        return food;
    }


}