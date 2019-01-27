package com.tribesbackend.tribes.services;
import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.ResourcesModel;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
        assertEquals(now.getTime(), testList.get(0).getTimeStampLastVisit());
        assertEquals(now.getTime(), testList.get(1).getTimeStampLastVisit());
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
    public void verifyResourceReturnTest(){
        Kingdom testKingdom = new Kingdom();
        ResourcesModel model = new ResourcesModel("gold",100, testKingdom);
        long id = 1;
        Optional<ResourcesModel> testOptional = Optional.of(model);
        Mockito.when(resourceRepository.findResourceByResourcesId(id)).thenReturn(testOptional);
        assertEquals(resourceService.verifyResource(id), model);
    }

    @Test(expected = IllegalArgumentException.class)
    public void verifyResourceEmptyOptionalTest(){
        long id = 1;
        Optional<ResourcesModel> emptyOptional = Optional.ofNullable(null);
        Mockito.when(resourceRepository.findResourceByResourcesId(id)).thenReturn(emptyOptional);
        resourceService.verifyResource(id);
    }

    @Test
    public void verifyResourceSecondTest(){
        Kingdom testKingdom = new Kingdom();
        ResourcesModel model = new ResourcesModel("gold",100, testKingdom);
        long id = 1;
        Optional<ResourcesModel> testOptional = Optional.of(model);
        Mockito.when(resourceRepository.findResourceByResourcesId(id)).thenReturn(testOptional);
        ResourcesModel verified = resourceService.verifyResource(id);
        assertEquals("gold", verified.getType());
    }

    @Test
    public void getLastTimestampFromDBTest(){

        Kingdom testKingdom = new Kingdom();
        ResourcesModel model = new ResourcesModel("gold",100, testKingdom);
        model.setTimeStampLastVisit(100);
        assertEquals(resourceService.getLastTimestampFromDB(model), new Timestamp(100));
    }

    @Test
    public void verifyTimestampHasValueZeroTimeTest(){
        ResourcesModel testModel = new ResourcesModel("gold", 0, testKingdom);
        testModel.setTimeStampLastVisit(0);
        long now = new Timestamp(System.currentTimeMillis()).getTime();
        long fromFnc = resourceService.verifyTimestampHasValue(testModel).getTime();
        assertEquals(now, fromFnc);
    }

    @Test
    public void verifyTimestampHasValueTest(){
        modelGold.setTimeStampLastVisit(1548322289246L);
        long directlyFromModel = modelGold.getTimeStampLastVisit();
        long fromFnc = resourceService.verifyTimestampHasValue(modelGold).getTime();
        assertEquals(directlyFromModel, fromFnc);
    }

    @Test
    public void getRMListFromDBTest(){
        testKingdom.setResourcesModel(resourceService.newUserResourcesPreFill(testKingdom));
        Mockito.when(kingdomService.verifyKingdom("Alex")).thenReturn(testKingdom);
        assertEquals(testKingdom.getResourcesModel().get(1), resourceService.getRMListFromDB("Alex").get(1));
    }
    //List<ResourcesModel> withTimeStamps = getRMListFromDB(username);
    //return TimeService.timeDifferenceInMin(verifyTimestampHasValue(withTimeStamps.get(0)), getCurrentTimestamp())
    @Test
    public void getDifferenceInMinutesZeroDiff(){
        testKingdom.setResourcesModel(resourceService.newUserResourcesPreFill(testKingdom));
        assertNotNull(testKingdom.getResourcesModel());
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Timestamp timeFromKingdom = resourceService.verifyTimestampHasValue(testKingdom.getResourcesModel().get(1));
        assertEquals(now, timeFromKingdom);
        //Mockito.when(resourceService.getRMListFromDB("Alex")).thenReturn(testKingdom.getResourcesModel());
        assertEquals(0L, resourceService.getDifferenceInMinutes("Alex"));
        /*Mockito.when(resourceService.getRMListFromDB("Alf")).thenReturn(testKingdom.getResourcesModel());
        //modelGold.setTimeStampLastVisit(1548322289246L);
        Timestamp stamp = new Timestamp(testKingdom.getResourcesModel().get(1).getTimeStampLastVisit());

        long differenceInMin = TimeUnit.MILLISECONDS.toMinutes(now.getTime() - stamp.getTime());
        Mockito.when(resourceService.getRMListFromDB("Alf")).thenReturn(testKingdom.getResourcesModel());
        //Mockito.when(resourceService.verifyTimestampHasValue(new Timestamp(testKingdom.getResourcesModel().get(1).getTimeStampLastVisit()))).thenReturn(stamp);

        assertEquals(differenceInMin, resourceService.getDifferenceInMinutes("Alf"));
        */
    }

    @Test
    public void resourceDisplayAndUpdateTest(){
        testKingdom.setResourcesModel(resourceService.newUserResourcesPreFill(testKingdom));

    }

}

