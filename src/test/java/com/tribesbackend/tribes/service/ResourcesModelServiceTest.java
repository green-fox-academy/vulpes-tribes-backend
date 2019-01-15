package com.tribesbackend.tribes.service;

import com.tribesbackend.tribes.tribesresources.model.ResourcesModel;
import com.tribesbackend.tribes.tribesresources.model.ResourceService;
import com.tribesbackend.tribes.tribesresources.repository.ResourceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class ResourcesModelServiceTest {

    MockMvc mockMvc;

    @Mock
    private ResourceRepository resourceRepository;

    @InjectMocks
    private ResourceService resourceService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(resourceService).build();
    }

    @Test
    public void getCurrentTimestampTest(){
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Timestamp toTest = resourceService.getCurrentTimestamp();
        assertEquals(now, toTest);
    }

//    @Test
//    public void verifyResource(){
//        ResourcesModel resourcesModel = new ResourcesModel("gold" );
//    }
}
