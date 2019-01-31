package com.tribesbackend.tribes.controllers;

import com.tribesbackend.tribes.factories.KingdomFactory;
import com.tribesbackend.tribes.models.jsonmodels.KingdomInputJson;
import com.tribesbackend.tribes.models.jsonmodels.KingdomResponseJson;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class KingdomRestControllerTest {
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
    private MockMvc mockMvc;
    @InjectMocks
    private KingdomRestController kingdomRestController;


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(kingdomRestController).build();
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("player", "heslo"));
    }

    @Mock
    private KingdomRepository kingdomRepository;

    @Mock
    private KingdomInputJson kingdomInputJson;
    
    @Mock
    private KingdomResponseJson kingdomResponseJson;



    @Test
    public void testGetKingdom() throws Exception {
        when(kingdomRepository.findKingdomByTribesUserUsername(any())).thenReturn(java.util.Optional.of(KingdomFactory.createValidSampleKingdom()));
        kingdomRestController.setKingdomRepository(kingdomRepository);
        mockMvc.perform(get("/kingdom"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testPutKingdom() throws Exception {

        String json = "{\n" +
                "  \"name\": \"azeho\",\n" +
                "\"locationX\":4,\n" +
                "\"locationY\":6\n" +
                "}";

        when(kingdomRepository.findKingdomByTribesUserUsername(any())).thenReturn(java.util.Optional
                .of(KingdomFactory.createValidSampleKingdom()));
        kingdomRestController.setKingdomRepository(kingdomRepository);
        mockMvc.perform(put("/kingdom")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("azeho")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location.x", Matchers.is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location.y", Matchers.is(6)))
                .andExpect(status().isOk())
                .andDo(print());

       // kingdomRepository.findKingdomByTribesUserUsername(any())

             //   .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Not enough resources")))

    }
    // original endpoint:
//    @PutMapping(value = "/kingdom")
//    public ResponseEntity putKingdom(@RequestBody KingdomInputJson kingdomInputJson) {
//        Kingdom kingdom = getCurrentKingdom();
//        kingdom.setName(kingdomInputJson.getName());
//        kingdom.setLocation(new Location(kingdomInputJson.getLocationX(), kingdomInputJson.getLocationY()));
//        kingdomRepository.save(kingdom);
//        return new ResponseEntity(new KingdomResponseJson(kingdom), HttpStatus.OK);
//    }


}
