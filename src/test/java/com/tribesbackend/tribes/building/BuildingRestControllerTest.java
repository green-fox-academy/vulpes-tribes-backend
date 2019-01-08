package com.tribesbackend.tribes.building;

import com.tribesbackend.tribes.tribesbuilding.controller.BuildingRestController;
import com.tribesbackend.tribes.tribesuser.okstatusservice.TokenIsValid;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.refEq;

@RunWith(SpringJUnit4ClassRunner.class)
public class BuildingRestControllerTest {
    @Mock
    private TokenIsValid validToken;
    @InjectMocks
    private BuildingRestController buildingRestController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(buildingRestController).build();
    }

    @Test
    public void testGettingBuildingsOfUser() throws Exception {
        String xTribesToken = "123";

        Mockito.when(validToken.isValid(refEq(xTribesToken))).thenReturn(true);
//Mockito.doNothing().when
        mockMvc.perform(MockMvcRequestBuilders.get("/kingdom/buildings")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(xTribesToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }


}
