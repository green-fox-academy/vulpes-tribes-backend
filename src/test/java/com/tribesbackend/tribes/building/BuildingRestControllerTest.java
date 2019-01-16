package com.tribesbackend.tribes.building;

import com.tribesbackend.tribes.tribesbuilding.controller.MockBuildingRestController;
import com.tribesbackend.tribes.tribesuser.okstatusservice.TokenIsValid;
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
public class BuildingRestControllerTest {
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

 //       Mockito.when(validToken.isValid(refEq(xTribesToken))).thenReturn(true);
//Mockito.doNothing().when
        mockMvc.perform(MockMvcRequestBuilders.get("/kingdom/buildings")
                .accept(MediaType.APPLICATION_JSON)
                .header("X-Tribes-Token", xTribesToken))
   //             .andDo(print())
  //      .andDo(Moc)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(xTribesToken))
//                .andDo(MockMvcResultMatchers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
     //   .andExpect(js);
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").exists());
         //       .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
        //     .;

    }


}
