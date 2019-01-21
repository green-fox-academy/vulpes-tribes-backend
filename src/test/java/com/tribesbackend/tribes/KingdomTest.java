package com.tribesbackend.tribes;

import com.tribesbackend.tribes.tribeskingdom.model.Kingdom;
import com.tribesbackend.tribes.tribeskingdom.model.KingdomModelHelpersMethods;
import com.tribesbackend.tribes.tribesuser.model.TribesUser;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KingdomTest {

    TribesUser testUser = new TribesUser("newName", "verysecure");


    @Test
    public void kingdomNameIsValid(){

        Kingdom kingdom = new Kingdom( "kingdom", testUser  );
        assertEquals(true, KingdomModelHelpersMethods.isValid(kingdom));
    }

    @Test
    public void kingdomNameIsNotValid(){
        Kingdom kingdom = new Kingdom( null, null );
        assertEquals( false, KingdomModelHelpersMethods.isValid(kingdom) );
    }
}
