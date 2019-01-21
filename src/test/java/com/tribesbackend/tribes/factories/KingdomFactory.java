package com.tribesbackend.tribes.factories;

import com.tribesbackend.tribes.models.Kingdom;
import org.springframework.beans.factory.annotation.Autowired;

public class KingdomFactory {

    @Autowired
    TribesUserFactory tribesUserFactory;

    public Kingdom createInvalidSampleKingdom(){
        return new Kingdom("name", tribesUserFactory.createInvalidSampleTribesUser());
    }

    public Kingdom createValidSampleKingdom(){
        return new Kingdom("name", tribesUserFactory.createValidSampleTribesUser());
    }
}
