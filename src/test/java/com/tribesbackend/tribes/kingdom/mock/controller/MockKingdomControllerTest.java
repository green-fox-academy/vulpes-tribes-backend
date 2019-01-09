package com.tribesbackend.tribes.kingdom.mock.controller;

import com.tribesbackend.tribes.tribeskingdom.controllers.MockKingdomController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
public class MockKingdomControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private MockKingdomController mockKingdomController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(mockKingdomController).build();
    }

    @Test
    public void testMockJson() throws Exception {
        String json = "{\"name\":\"mockdom\",\"tribesUser\":{\"username\":\"mockUser\",\"password\":\"strongOne\"},\"resources\":null}";
        mockMvc.perform(MockMvcRequestBuilders.get("/mock/kingdom"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(json));


    }
}




