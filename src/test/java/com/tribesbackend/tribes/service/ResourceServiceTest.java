package com.tribesbackend.tribes.service;

import com.tribesbackend.tribes.tribesresource.model.Resource;
import com.tribesbackend.tribes.tribesresource.model.ResourceService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class ResourceServiceTest {

    ResourceService resourceService;

    @Test
    public void getCurrentTimestampTest(){
        Timestamp now = new Timestamp(System.currentTimeMillis());
        assertEquals(now, resourceService.)
    }
}
