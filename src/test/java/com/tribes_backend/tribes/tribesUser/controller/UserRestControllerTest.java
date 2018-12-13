package com.tribes_backend.tribes.tribesUser.controller;

import static org.junit.Assert.*;
import com.tribes_backend.tribes.tribesUser.model.UserModelHelpersMethods;
import com.tribes_backend.tribes.tribesUser.controller.UserRestController;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserRestControllerTest {

    private MockMvc mockMvc;
    @Mock
    private UserModelHelpersMethods userMethods;
    @InjectMocks
    private UserRestController userRestController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(userRestController).build();
    }

    @Test
    public void testRegisterUser() throws Exception {
        String json = "{\n" +
                "  \"username\": \"adamgyulavari\",\n" +
                "  \"password\": \"abc123\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is("adamgyulavari")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.is("abc123")));
    }

}