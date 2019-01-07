package com.tribesbackend.tribes.tribesresource.model;

public class ResourcesFactory {
    public static Resources createValidSampleResource() {
        return new Resources.ResourcesBuilder()
                .setAmount( 20 )
                .setUpdated_at( 1234 )
                .setType("gold")
                .build();
    }

    public static Resources createInvalidSampleResource() {
        return new Resources.ResourcesBuilder()
                .setAmount( 5 )
                .setUpdated_at( 987654321 )
                .setType("silver")
                .build();
    }
}
