package com.tribesbackend.tribes.service;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class TimeService {

    public static long timeDifferenceInMin(Timestamp timestamp1, Timestamp timestamp2) {
        long milliseconds = timestamp2.getTime() - timestamp1.getTime();
        long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds);
        return minutes;

    }

    public Integer buildingTimeInMin (String type, int level) {
        if (level >= 1 && level <= 5) {
            switch (type) {
                case "townhall":
                    return 3 * (1 + (level - 1) * 2);
                case "mine":
                    return 3 * (1 + (level - 1) * 2);
                case "farm":
                    return 3 * (1 + (level - 1) * 2);
                case "barracks":
                    return 3 * (1 + (level - 1) * 2);
            }
            throw new IllegalArgumentException("Wrong type of the building");
        } else throw new IllegalArgumentException("Wrong level of the building");
    }

}