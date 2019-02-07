package com.tribesbackend.tribes.factories;

import com.tribesbackend.tribes.models.Kingdom;

import java.util.Optional;

public class KingdomFactory {


    public static Kingdom createInvalidSampleKingdom(){
        return new Kingdom("name", TribesUserFactory.createInvalidSampleTribesUser());
    }

    public static Kingdom createValidSampleKingdom(){
        return new Kingdom("mightykingdom", TribesUserFactory.createValidSampleTribesUser());
    }

    public static Optional<Kingdom> createOptionalValidSampleKingdom() {
        Optional<Kingdom> kingdom = Optional.of(createValidSampleKingdom());
        kingdom.get().setId((long)1);
        return kingdom;
    }
}
