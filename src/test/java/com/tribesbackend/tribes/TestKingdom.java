package com.tribesbackend.tribes;

import com.tribesbackend.tribes.tribeskingdom.model.Kingdom;
import com.tribesbackend.tribes.tribeskingdom.model.KingdomModelHelpersMethods;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestKingdom {

    @Test
    public void kingdoNameIsValid(){
        Kingdom kingdom = new Kingdom( "kingdom" );
        assertEquals(true, KingdomModelHelpersMethods.isValid(kingdom));
    }

    @Test
    public void kingdoNameIsNotValid(){
        Kingdom kingdom = new Kingdom( null );
        assertEquals( false, KingdomModelHelpersMethods.isValid(kingdom) );
    }
}
