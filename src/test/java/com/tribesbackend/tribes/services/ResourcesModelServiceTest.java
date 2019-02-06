package com.tribesbackend.tribes.services;
import com.tribesbackend.tribes.factories.BuildingFactory;
import com.tribesbackend.tribes.factories.KingdomFactory;
import com.tribesbackend.tribes.models.Building;
import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.ResourcesModel;
import com.tribesbackend.tribes.models.TribesUser;
import com.tribesbackend.tribes.repositories.BuildingRepository;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import com.tribesbackend.tribes.services.resourcesservice.ResourceService;
import com.tribesbackend.tribes.repositories.ResourceRepository;
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

import static com.tribesbackend.tribes.factories.KingdomFactory.createValidSampleKingdom;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class ResourcesModelServiceTest {

    private TribesUser testUser = new TribesUser("Alex", "123");
    private Kingdom testKingdom = new Kingdom("mockDom", testUser);
    private ResourcesModel modelGold = new ResourcesModel("gold",380, testKingdom);
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

    @Mock
    private BuildingRepository buildingRepository;

    BuildingFactory buildingFactory = new BuildingFactory();

    KingdomFactory kingdomFactory = new KingdomFactory();

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
        assertEquals(now.getTime(), testList.get(0).getUpdatedAt());
        assertEquals(2, testList.size());
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
    public void filterMinesBuildings(){
        List<Building> buildings = buildingFactory.giveMeSomeBuildingsList();
        Mockito.when(buildingRepository.findAllByKingdom(testKingdom)).thenReturn(buildings);
        List<Building> mineList = resourceService.getGoldResources(testKingdom);
        assertNotNull(mineList);
        assertFalse(mineList.isEmpty());
        assertEquals(2, mineList.size());
        assertEquals("mine", mineList.get(0).getType());
        assertEquals("mine", mineList.get(1).getType());
    }

    @Test
    public void getsumofBuildingLevelsTest(){
        List<Building> buildings = buildingFactory.giveMeSomeBuildingsList();
        Kingdom testKingdom = createValidSampleKingdom();
        testKingdom.setBuildings(buildings);
        int mysteriousOne = resourceService.getSumOfBuildingLevels(testKingdom, "mine");
        assertEquals(3, mysteriousOne);
    }

    @Test
    public void resourceDisplayAndUpdateTest(){
        testKingdom.setResourcesModel(resourceService.newUserResourcesPreFill(testKingdom));
        List<ResourcesModel> listResources = testKingdom.getResourcesModel();
        long someTime = 1549256400000L;
        listResources.get(0).setUpdatedAt(someTime);
        assertEquals(2, listResources.size());
        assertEquals(1549256400000L, listResources.get(0).getUpdatedAt());
        Mockito.when(kingdomService.verifyKingdom("Alex")).thenReturn(testKingdom);
        List<Building> buildings = buildingFactory.giveMeSomeBuildingsList();
        testKingdom.setBuildings(buildings);
        long resourceBefore = testKingdom.getResourcesModel().get(0).getAmount();
        assertFalse(testKingdom.getBuildings().isEmpty());

//       assertEquals(resourceService.resourceDisplayandUpdate("Alex")
//       .get(0).getUpdatedAt(), new Timestamp(System.currentTimeMillis()).getTime());
       assertTrue(resourceService.resourceDisplayandUpdate("Alex")
               .get(0).getAmount() > 10733);

    }

}

