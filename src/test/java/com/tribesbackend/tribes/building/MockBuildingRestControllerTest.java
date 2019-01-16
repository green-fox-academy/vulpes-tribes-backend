package com.tribesbackend.tribes.building;

import com.tribesbackend.tribes.tribesbuilding.controller.MockBuildingRestController;
import com.tribesbackend.tribes.tribesuser.okstatusservice.TokenIsValid;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
public class MockBuildingRestControllerTest {
    @Mock
    private TokenIsValid validToken;

    private MockMvc mockMvc;

    @InjectMocks
    private MockBuildingRestController mockBuildingRestController;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(mockBuildingRestController).build();
    }

    @Test
    public void testGettingBuildingsOfUser() throws Exception {
        String xTribesToken = "1";
        mockMvc.perform(MockMvcRequestBuilders.get("/kingdom/buildings")
                .accept(MediaType.APPLICATION_JSON)
                .header("X-Tribes-Token", xTribesToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").exists());
    }

    @Test
    public void testGettingTokenInvalid() throws Exception {
        String xTribesToken = "1234";
        mockMvc.perform(MockMvcRequestBuilders.get("/kingdom/buildings")
                .accept(MediaType.APPLICATION_JSON)
                .header("X-Tribes-Token", xTribesToken))
                .andExpect(MockMvcResultMatchers.status().is(400))
             .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
    }

    @Test
    public void testPostingBuildingOfUser() throws Exception{
        String xTribesToken = "1";
        String type = "farm";
        mockMvc.perform(MockMvcRequestBuilders.post("/kingdom/buildings")
                .accept(MediaType.APPLICATION_JSON)
                .header("X-Tribes-Token", xTribesToken)
                .content(type))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.type", Matchers.is("farm")));
    }


}
