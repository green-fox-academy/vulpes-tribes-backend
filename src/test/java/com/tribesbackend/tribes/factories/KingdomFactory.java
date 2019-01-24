package com.tribesbackend.tribes.factories;

import com.tribesbackend.tribes.models.Kingdom;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class KingdomFactory {

    public static Kingdom createInvalidSampleKingdom(){
        return new Kingdom("name", TribesUserFactory.createInvalidSampleTribesUser());
    }

    public static Kingdom createValidSampleKingdom(){
        return new Kingdom("name", TribesUserFactory.createValidSampleTribesUser());
    }

    public static Optional<Kingdom> createOptionalValidSampleKingdom() {
        return Optional.of(createValidSampleKingdom());
    }
}
