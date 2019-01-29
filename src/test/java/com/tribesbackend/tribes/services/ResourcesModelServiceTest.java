package com.tribesbackend.tribes.services;
import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.Resources.ResourcesModel;
import com.tribesbackend.tribes.models.TribesUser;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import com.tribesbackend.tribes.services.resourcesservice.ResourceService;
import com.tribesbackend.tribes.repositories.ResourceRepository;
import com.tribesbackend.tribes.services.timeservice.TimeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.sql.Timestamp;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class ResourcesModelServiceTest {

    private TribesUser testUser = new TribesUser("Alex", "123");
    private Kingdom testKingdom = new Kingdom("mockDom", testUser);
    private ResourcesModel modelGold = new ResourcesModel("gold",100, testKingdom);
    private ResourcesModel modelFood = new ResourcesModel("food", 0, testKingdom);
    private List<ResourcesModel> listOfResources = Arrays.asList(modelGold, modelFood);

    @Mock
    private ResourceRepository resourceRepository;

    @Mock
    private KingdomRepository kingdomRepository;

    @Mock
    private TimeService timeService;

    @Mock
    private KingdomService kingdomService;

    @InjectMocks
    private ResourceService resourceService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void newUserResourcesPreFill(){
        Kingdom kingdom = new Kingdom();
        List<ResourcesModel> testList = resourceService.newUserResourcesPreFill(kingdom);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        assertEquals(2, testList.size());
        assertEquals(now.getTime(), testList.get(0).getUpdatedAt());
        assertEquals(now.getTime(), testList.get(1).getUpdatedAt());
        assertEquals("gold", testList.get(0).getType());
        assertEquals("food", testList.get(1).getType());
        assertEquals(380, testList.get(0).getAmount());
        assertEquals(0, testList.get(1).getAmount());
    }

    @Test
    public void extract(){
        testKingdom.setResourcesModel(listOfResources);
        assertFalse(testKingdom.getResourcesModel().isEmpty());
        assertEquals(2, testKingdom.getResourcesModel().size());
        //assertEquals(testKingdom.getResourcesModel(), resourceService.extractResourceFromKingdom("Alf"));
    }


    @Test
    public void getCurrentTimestampTest() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Timestamp toTest = resourceService.getCurrentTimestamp();
        assertEquals(now, toTest);
    }

    @Test
    public void verifyResourceOptionalTest() {
        Optional<ResourcesModel> testOptional = Optional.of(modelGold);
        Optional<ResourcesModel> emptyOptional = Optional.ofNullable(null);
        assertEquals(testOptional.get(), modelGold);
        assertFalse(emptyOptional.isPresent());
    }


    @Test
    public void resourceDisplayAndUpdateTest(){
        testKingdom.setResourcesModel(resourceService.newUserResourcesPreFill(testKingdom));

    }

}

