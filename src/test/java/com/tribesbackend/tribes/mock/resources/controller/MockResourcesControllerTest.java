//package com.tribesbackend.tribes.mock.resources.controller;
//
//import com.tribesbackend.tribes.tribesresources.controller.MockResourcesController;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//public class MockResourcesControllerTest {
//
//    private MockMvc mockMvc;
//
//    @InjectMocks
//    private MockResourcesController mockResourcesController;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(mockResourcesController).build();
//    }
//
//    @Test
//    public void testMockJson() throws Exception {
//        String json = "{\"type\":\"gold\",\"amount\":100}";
//        mockMvc.perform(MockMvcRequestBuilders.get("/mock/resources"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string(json));
//    }
//}
