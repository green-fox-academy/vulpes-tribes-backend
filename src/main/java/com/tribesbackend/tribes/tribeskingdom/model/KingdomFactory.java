package com.tribesbackend.tribes.tribeskingdom.model;

import com.tribesbackend.tribes.tribesuser.model.TribesUserFactory;

public class KingdomFactory {

    public static Kingdom createInvalidSampleKingdom(){
        return new Kingdom("name", TribesUserFactory.createInvalidSampleTribesUser());
    }

    public static Kingdom createValidSampleKingdom(){
        return new Kingdom("name", TribesUserFactory.createValidSampleTribesUser());
    }
}
