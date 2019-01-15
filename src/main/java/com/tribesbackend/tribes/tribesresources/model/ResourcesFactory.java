package com.tribesbackend.tribes.tribesresources.model;


public class ResourcesFactory {
    public static ResourcesModel createValidSampleResources() {
        return new ResourcesModel.ResourcesBuilder()
                .setAmount( 20 )
                .setType("gold")
                .build();
    }

    public static ResourcesModel createInvalidAmountSampleResources() {
        return new ResourcesModel.ResourcesBuilder()
                .setAmount( -5 )
                .setType("gold")
                .build();
    }

    public static ResourcesModel createInvalidUpdated_atSampleResources() {
        return new ResourcesModel.ResourcesBuilder()
                .setAmount( 5 )
                .setType("gold")
                .build();
    }

    public static ResourcesModel createInvalidTypeSampleResources() {
        return new ResourcesModel.ResourcesBuilder()
                .setAmount( 5 )
                .setType("silver")
                .build();
    }
}