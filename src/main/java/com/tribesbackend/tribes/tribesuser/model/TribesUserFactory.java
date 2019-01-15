package com.tribesbackend.tribes.tribesuser.model;

public class TribesUserFactory {

    public static TribesUser createValidSampleTribesUser(){
        return new TribesUser.TribesUserBuilder()
                .setPassword("password")
                .setPassword("1234")
                .build();
    }

    public static TribesUser createInvalidSampleTribesUser(){
        return new TribesUser.TribesUserBuilder()
                .setPassword(null)
                .setPassword("1234")
                .build();
    }
}
