package com.tribesbackend.tribes.services;

import com.tribesbackend.tribes.models.BuildingTime;
import com.tribesbackend.tribes.repositories.BuildingTimeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Calendar;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class TimeServiceTest {

    @InjectMocks
    TimeService timeService;
    @Mock
    BuildingTimeRepository buildingTimeRepository;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(timeService).build();
    }

    @Test
    public void timeDifference10min() {
        long timestamp1 = System.currentTimeMillis();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp1);

        // add some seconds to the calendar
        cal.add(Calendar.SECOND, 600);
        long timestamp2 = cal.getTime().getTime();
        assertEquals(10, timeService.timeDifferenceInMin(timestamp1, timestamp2));
    }

    @Test
    public void timeDifferenceNegative() {
        long timestamp1 = System.currentTimeMillis();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp1);

        // add some seconds to the calendar
        cal.add(Calendar.SECOND, -600);
        long timestamp2 = cal.getTime().getTime();
        assertEquals(-10, timeService.timeDifferenceInMin(timestamp1, timestamp2));
    }

    @Test
    public void timeDifferenceNone() {
        long timestamp1 = System.currentTimeMillis();
        long timestamp2 = System.currentTimeMillis();
        assertEquals(0, timeService.timeDifferenceInMin(timestamp1, timestamp2));
    }

    @Test
    public void farmTimeTest() {
        Optional<BuildingTime> buildingTime = Optional.of(new BuildingTime("farm", 1));
        Mockito.when(buildingTimeRepository.findByType("farm")).thenReturn(buildingTime);
        assertEquals(2, timeService.buildingTimeInMin("farm", 2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void buildingTimeInvalidLevelTest() {
        assertEquals(2, timeService.buildingTimeInMin("farm", 7));
    }

    @Test(expected = IllegalArgumentException.class)
    public void buildingTimeInvalidTypeTest() {
        assertEquals(2, timeService.buildingTimeInMin("farma", 2));
    }

    @Test
    public void troopTimeTest() {
        assertEquals(0.5, timeService.troopTimeinMin(1, 1), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void troopTimeInvalidLevelTest() {
        assertEquals(0.5, timeService.troopTimeinMin(0, 3), 0.001);
    }
}
