package com.tribesbackend.tribes;

import com.tribesbackend.tribes.tribeskingdom.model.Kingdom;
import com.tribesbackend.tribes.tribesuser.model.TribesUser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TribesKingdomTest {

    TribesUser testUser;

    @Test
    public void kingdomNameIsValid(){
        Kingdom kingdom = new Kingdom( "kingdom", testUser );
        assertEquals(true, KingdomModelHelpersMethods.isValid(kingdom));
    }

    @Test
    public void kingdomNameIsNotValid(){
        Kingdom kingdom = new Kingdom( null, testUser );
        assertEquals( false, KingdomModelHelpersMethods.isValid(kingdom) );
    }
}
