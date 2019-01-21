package com.tribesbackend.tribes.service;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class TimeService {

    public static long timeDifferenceInMin(Timestamp timestamp1, Timestamp timestamp2) {
        long milliseconds = timestamp2.getTime() - timestamp1.getTime();
        long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds);
        return minutes;

    }
}
