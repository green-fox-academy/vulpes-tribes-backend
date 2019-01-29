package com.tribesbackend.tribes.services;

import com.tribesbackend.tribes.repositories.BuildingTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

@Service
public class TimeService {

    @Autowired
    BuildingTimeRepository buildingTimeRepository;

    public static long timeDifferenceInMin(Timestamp timestamp1, Timestamp timestamp2) {
        long milliseconds = timestamp2.getTime() - timestamp1.getTime();
        long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds);
        return minutes;
    }

    public int buildingTimeInMin(String type, int level) {
        if (level >= 1 && level <= 5) {
            if (buildingTimeRepository.findByType(type).isPresent()) {
                return buildingTimeRepository.findByType(type).get().getBuildTimeInMin() * level;
            } else throw new IllegalArgumentException();
        } else throw new IllegalArgumentException();
    }

    public Double troopTimeinMin(int troopLevel, int barracksLevel) {
        if ((troopLevel >= 1 && troopLevel <= 5) && (barracksLevel >= 1 && barracksLevel <= 5)) {
            double value = troopLevel / (2.0 * barracksLevel);
            BigDecimal bd = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
            value = bd.doubleValue();
            return value;
        } else throw new IllegalArgumentException();
    }
}
