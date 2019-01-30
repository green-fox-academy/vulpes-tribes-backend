package com.tribesbackend.tribes.services;

import com.tribesbackend.tribes.models.ItemPrice;
import com.tribesbackend.tribes.repositories.ItemPriceRepository;
import com.tribesbackend.tribes.repositories.ResourceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class PurchaseServiceTest {

    @InjectMocks
    private PurchaseService purchaseService;
    @Mock
    private ItemPriceRepository itemPriceRepository;
    @Mock
    ResourceRepository resourceRepository;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(purchaseService).build();
    }

    @Test
    public void priceOfTownhallLevelOneTest() {
        Optional<ItemPrice> itemPrice = Optional.of(new ItemPrice("townhall", 300));
        Mockito.when(itemPriceRepository.findByType("townhall")).thenReturn(itemPrice);
        assertEquals(300, purchaseService.priceOfItem("townhall", 1));
    }

    @Test
    public void priceOfFarmLevelFiveTest() {
        Optional<ItemPrice> itemPrice = Optional.of(new ItemPrice("farm", 80));
        Mockito.when(itemPriceRepository.findByType("farm")).thenReturn(itemPrice);
        assertEquals(400, purchaseService.priceOfItem("farm", 5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongLevelOfFarmTest() {
        int gold = purchaseService.priceOfItem("farm", 9);
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongTypeOfItemTest() {
        int gold = purchaseService.priceOfItem("aze", 2);
    }

//    @Test
//    public void purchasableFarmLevel2Test() {
//        Optional<ItemPrice> itemPrice = Optional.of(new ItemPrice("farm", 80));
//        Mockito.when(itemPriceRepository.findByType("farm")).thenReturn(itemPrice);
//        Kingdom sampleKingdom = KingdomFactory.createValidSampleKingdom();
//        long id = 1;
//        sampleKingdom.setId(id);
//        Optional<ResourcesModel> resourcesModel = Optional.of(new ResourcesModel("gold", sampleKingdom));
//        Mockito.when(resourceRepository.findByKingdom_IdAndType(sampleKingdom.getId(), "gold")).thenReturn(resourcesModel);
//        assertTrue(purchaseService.purchasableItem(sampleKingdom.getId(), "farm", 1));
//    }
}