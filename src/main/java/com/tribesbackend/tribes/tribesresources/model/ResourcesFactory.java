package com.tribesbackend.tribes.tribesresources.model;

package com.tribesbackend.tribes.tribesresources.model;

public class ResourcesFactory {
    public static Resources createValidSampleResources() {
        return new Resources.ResourcesBuilder()
                .setAmount( 20 )
                .setUpdated_at( 1234 )
                .setType("gold")
                .build();
    }

    public static Resources createInvalidAmountSampleResources() {
        return new Resources.ResourcesBuilder()
                .setAmount( -5 )
                .setUpdated_at( 987654321 )
                .setType("gold")
                .build();
    }

    public static Resources createInvalidUpdated_atSampleResources() {
        return new Resources.ResourcesBuilder()
                .setAmount( 5 )
                .setUpdated_at( -987654321 )
                .setType("gold")
                .build();
    }

    public static Resources createInvalidTypeSampleResources() {
        return new Resources.ResourcesBuilder()
                .setAmount( 5 )
                .setUpdated_at( 987654321 )
                .setType("silver")
                .build();
    }
}