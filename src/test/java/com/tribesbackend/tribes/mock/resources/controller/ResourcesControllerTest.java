package com.tribesbackend.tribes.mock.resources.controller;

import com.tribesbackend.tribes.controllers.resourcecontrollers.ResourcesRestController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
public class ResourcesControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ResourcesRestController resourcesRestController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(resourcesRestController).build();
    }

    @Test
    public void testMockJson() throws Exception {
        String json = "{\"resources\":[{\"type\":\"gold\",\"amount\":100,\"generation\":1},{\"type\":\"food\",\"amount\":100,\"generation\":1}]}";
        mockMvc.perform(MockMvcRequestBuilders.get("/mock/kingdom/resources"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(json));
    }
}
