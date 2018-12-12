package com.tribes_backend.tribes.controller;

import com.tribes_backend.tribes.model.TribesUser;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRestControllerTest {

    private MockMvc mockMvc;

//    @Mock

    @InjectMocks
    private UserController userController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testLogin() throws Exception {
        //language=JSON
        String json = "{\n" +
                "  \"username\": \"adamgyulavari\",\n" +
                "  \"password\": \"abc123\"\n" +
                "}";
//        Mockito.when().thenReturn();

        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

//    @Test
//    public void testLogin() throws Exception {
//        //language=JSON
//        String json = "{\n" +
//                "  \"username\": \"adamgyulavari\",\n" +
//                "  \"password\": \"abc123\"\n" +
//                "}";
////        Mockito.when().thenReturn();
//
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/login")
//            .contentType(MediaType.APPLICATION_JSON)
//                .content(json)
//        )
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//        String content = result.getResponse().getContentAsString();
//    }

    @Test
    public void testRegister() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/register").accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is("newUser")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", Matchers.hasSize(3)));
    }
}