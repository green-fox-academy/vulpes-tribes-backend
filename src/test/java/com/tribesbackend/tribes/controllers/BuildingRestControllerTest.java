package com.tribesbackend.tribes.controllers;

import com.tribesbackend.tribes.factories.BuildingFactory;
import com.tribesbackend.tribes.factories.KingdomFactory;
import com.tribesbackend.tribes.factories.TribesUserFactory;
import com.tribesbackend.tribes.models.Building;
import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.TribesUser;
import com.tribesbackend.tribes.repositories.BuildingRepository;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import com.tribesbackend.tribes.services.PurchaseService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Optional;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringJUnit4ClassRunner.class)
public class BuildingRestControllerTest {

    @Mock
    private KingdomRepository kingdomRepository;

    @Mock
    private BuildingRepository buildingRepo;
    @Mock
    PurchaseService purchaseService;
    @InjectMocks
    private BuildingRestController buildingRestController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(buildingRestController).build();
        TribesUser testUser = TribesUserFactory.createValidSampleTribesUser();
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(testUser.getUsername(), testUser.getPassword()));
    }

//    @Test
//    public void createBuildingTest() throws Exception {
//        String json = "{\n" +
//                "  \"type\": \"farm\"\n" +
//                "}";
//
//        Kingdom mightykingdom = KingdomFactory.createValidSampleKingdom();
//        mightykingdom.setId((long) 1);
//        Mockito.when(kingdomRepository.findKingdomByTribesUserUsername("Vojtisek")).thenReturn(Optional.of(mightykingdom));
//        buildingRestController.setKingdomRepository(kingdomRepository);
//        Mockito.when(purchaseService.purchasableItem(mightykingdom.getId(), "farm", 1)).thenReturn(true);
//        mockMvc.perform(MockMvcRequestBuilders.post("/kingdom/buildings")
//                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//                .content(json))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.type", Matchers.is("farm")))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.level", Matchers.is(1)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.hp", Matchers.is(100)));
//    }


//    @Test
//    public void buildingListTest() throws Exception {
//
////        Optional<Kingdom> kingdom = KingdomFactory.createOptionalValidSampleKingdom();
////        Troop troop = TroopFactory.createSampleTroop();
////        kingdom.get().getTroops().add(troop);
////
////        Mockito.when(kingdomRepository.findKingdomByTribesUserUsername("Vojtisek")).thenReturn(kingdom);
////        mockMvc.perform(MockMvcRequestBuilders.get("/kingdom/troops"))
////                .andExpect(MockMvcResultMatchers.status().isOk());
////
////        when(kingdomRepository.findKingdomByTribesUserUsername("Vojtisek")).thenReturn(kingdom);
////        mockMvc.perform(get("/kingdom/troops"))
////                .andExpect(status().isOk())
////
////                .andExpect(jsonPath("$.troops").isArray())
////                .andExpect(content().string("{\"troops\":[{\"id\":null,\"level\":1,\"hp\":100,\"attack\":50,\"defence\":20,\"startedAt\":1231232312,\"finishedAt\":765214612}]}"));
//
//
//
//
////        Optional<Troop> troop = Optional.ofNullable(TroopFactory.createSampleTroop());
////
////        Kingdom mightykingdom = KingdomFactory.createValidSampleKingdom();
////        mightykingdom.setId((long) 1);
//
//
//
//        Optional<Kingdom> kingdom = KingdomFactory.createOptionalValidSampleKingdom();
//        Building building = BuildingFactory.createSampleBuilding();
//        kingdom.get().getBuildings().add(building);
//
//        when(kingdomRepository.findKingdomByTribesUserUsername("Vojtisek")).thenReturn(kingdom);
//        mockMvc.perform(get("/kingdom/buildings"))
//                .andExpect(status().isOk());
//
//        when(kingdomRepository.findKingdomByTribesUserUsername("Vojtisek")).thenReturn(kingdom);
//        mockMvc.perform(get("/kingdom/buildings"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.buildings").isArray())
//                .andExpect(content().string("{\"buildings\":[{\"id\":null,\"level\":1,\"hp\":100,\"attack\":50,\"defence\":20,\"startedAt\":1231232312,\"finishedAt\":765214612}]}"));
//    }



    @Test
    public void notResourcesTest() throws Exception {
        String json = "{\n" +
                "  \"type\": \"farm\"\n" +
                "}";
        Kingdom mightykingdom = KingdomFactory.createValidSampleKingdom();
        mightykingdom.setId((long) 1);
        when(kingdomRepository.findKingdomByTribesUserUsername("Vojtisek")).thenReturn(Optional.of(mightykingdom));
        buildingRestController.setKingdomRepository(kingdomRepository);
        when(purchaseService.purchasableItem(mightykingdom.getId(), "farm", 1)).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.post("/kingdom/buildings")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message", Matchers.is("Not enough resources")))
                .andExpect(jsonPath("$.status", Matchers.is("error")));
    }

    @Test
    public void invalidTypeTest() throws Exception {
        String json = "{\n" +
                "  \"type\": \"farmicka\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/kingdom/buildings")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json))
                .andExpect(status().isNotAcceptable())
                .andExpect(jsonPath("$.message", Matchers.is("Invalid building type")))
                .andExpect(jsonPath("$.status", Matchers.is("error")));
    }

    @Test
    public void missingParameterTest() throws Exception {
        String json = "{\n" +
                "  \"type\": \"\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/kingdom/buildings")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", Matchers.is("Missing parameter(s): type!")))
                .andExpect(jsonPath("$.status", Matchers.is("error")));
    }
//    @GetMapping(value = "/kingdom/buildings/{id}")
//    @ResponseBody
//    public ResponseEntity<Object> listTheBuilding(@PathVariable long id) {
//        if (buildingRepo.findById(id).isPresent()) {
//            return new ResponseEntity(buildingRepo.findById(id), HttpStatus.OK);
//        } else return new ResponseEntity(new ErrorResponseModel("Id not found"),
//                HttpStatus.NOT_FOUND);
//    }

//    @Test
//    public void buildingUploadedOK() throws Exception {
//        String buildingInputJson = "{\n" +
//                "  \"level\": \"2\"\n" +
//                "}";
//        Optional<Kingdom> kingdom = KingdomFactory.createOptionalValidSampleKingdom();
//        Mockito.when(kingdomRepository.findKingdomByTribesUserUsername("Vojtisek")).thenReturn(kingdom);
//        buildingRestController.setKingdomRepository(kingdomRepository);
//        when(kingdomRepository.findKingdomById(any())).thenReturn(Optional.empty());
//        Building farma = new Building("farm");
//        farma.setId(3L);
//        farma.setLevel(1);
//        Mockito.when(buildingRepo.findById(2L)).thenReturn(Optional.of(farma));
//        Mockito.when(purchaseService.purchasableItem(2l, "gold", 2)).thenReturn(true);
//        Mockito.when(purchaseService.priceOfItem("gold", 2)).thenReturn((long) 2);
//        Mockito.when(purchaseService.currentGoldAmount(1L)).thenReturn(3L);
//        mockMvc.perform(MockMvcRequestBuilders.put("/kingdom/buildings/{id}", "2")
//                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//                .content(buildingInputJson))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.type", Matchers.is("farm")))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(3)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.level", Matchers.is(2)));
//    }

    @Test
    public void missingParametr() throws Exception {
        String buildingInputJson = "{\n" +
                "  \"level\": \"\"\n" +
                "}";
        Optional<Kingdom> kingdom = KingdomFactory.createOptionalValidSampleKingdom();
        Mockito.when(kingdomRepository.findKingdomByTribesUserUsername("Vojtisek")).thenReturn(kingdom);
        buildingRestController.setKingdomRepository(kingdomRepository);
        when(kingdomRepository.findKingdomById(any())).thenReturn(Optional.empty());
        Building farma = new Building("farm");
        farma.setId(3L);
        farma.setLevel(1);
        Mockito.when(buildingRepo.findById(2L)).thenReturn(Optional.of(farma));
        Mockito.when(purchaseService.purchasableItem(2l, "gold", 2)).thenReturn(true);
        Mockito.when(purchaseService.priceOfItem("gold", 2)).thenReturn((long) 2);
        Mockito.when(purchaseService.currentGoldAmount(1L)).thenReturn(3L);
        mockMvc.perform(MockMvcRequestBuilders.put("/kingdom/buildings/{id}", "2")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(buildingInputJson))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is("error")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Missing parameter(s): level !")));

    }

    @Test
    public void idNotFound() throws Exception {
        String buildingInputJson = "{\n" +
                "  \"level\": \"2\"\n" +
                "}";
        //buildingRepo.findById(id).isPresent()))
        Optional<Kingdom> kingdom = KingdomFactory.createOptionalValidSampleKingdom();
        Mockito.when(kingdomRepository.findKingdomByTribesUserUsername("Vojtisek")).thenReturn(kingdom);
        buildingRestController.setKingdomRepository(kingdomRepository);
        //   when((buildingRepo.findById(156L).isPresent())).thenReturn(Optional <T>false); any())
        when(kingdomRepository.findKingdomById(2L)).thenReturn(Optional.empty());
//        Building farma = new Building("farm");
//        farma.setId(3L);
//        farma.setLevel(1);
//Optional.of(farma)
        Mockito.when(buildingRepo.findById(156L)).thenReturn(Optional.empty());
        Mockito.when(purchaseService.purchasableItem(156L, "gold", 2)).thenReturn(false);
        Mockito.when(purchaseService.priceOfItem("gold", 2)).thenReturn((long) 2);
        Mockito.when(purchaseService.currentGoldAmount(1L)).thenReturn(3L);
        mockMvc.perform(MockMvcRequestBuilders.put("/kingdom/buildings/{id}", "156")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(buildingInputJson))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is("error")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Id not found")));

    }
}




