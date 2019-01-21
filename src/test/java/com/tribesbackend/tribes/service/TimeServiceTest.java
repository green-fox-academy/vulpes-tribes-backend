package com.tribesbackend.tribes.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.sql.Timestamp;

import java.sql.Date;
import java.time.Instant;
import java.util.Calendar;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
public class TimeServiceTest {

    @Test
    public void timeDifference10min() {
        Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp1.getTime());

        // add some seconds to the calendar
        cal.add(Calendar.SECOND, 600);
        Timestamp timestamp2 = new Timestamp(cal.getTime().getTime());
        assertEquals(10, TimeService.timeDifferenceInMin(timestamp1, timestamp2));
    }

    @Test
    public void timeDifferenceNegative() {
        Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp1.getTime());

        // add some seconds to the calendar
        cal.add(Calendar.SECOND, -600);
        Timestamp timestamp2 = new Timestamp(cal.getTime().getTime());
        assertEquals(-10, TimeService.timeDifferenceInMin(timestamp1, timestamp2));
    }

    @Test
    public void timeDifferenceNone() {
        Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
        Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
        assertEquals(0, TimeService.timeDifferenceInMin(timestamp1, timestamp2));
    }

}