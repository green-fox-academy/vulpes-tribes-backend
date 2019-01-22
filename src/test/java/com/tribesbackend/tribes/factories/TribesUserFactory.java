package com.tribesbackend.tribes.factories;

import com.tribesbackend.tribes.models.TribesUser;
import org.springframework.stereotype.Component;

@Component
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
