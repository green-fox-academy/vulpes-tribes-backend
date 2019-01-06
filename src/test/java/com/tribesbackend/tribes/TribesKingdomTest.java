package com.tribesbackend.tribes;

import com.tribesbackend.tribes.tribeskingdom.model.Kingdom;
import com.tribesbackend.tribes.tribeskingdom.model.KingdomModelHelpersMethods;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TribesKingdomTest {

    @Test
    public void kingdomNameIsValid(){
        Kingdom kingdom = new Kingdom( "kingdom" );
        assertEquals(true, KingdomModelHelpersMethods.isValid(kingdom));
    }

    @Test
    public void kingdomNameIsNotValid(){
        Kingdom kingdom = new Kingdom( null );
        assertEquals( false, KingdomModelHelpersMethods.isValid(kingdom) );
    }

    @Test
    public void kingdomNameIsEmpty(){
        Kingdom kingdom = new Kingdom( "" );
        assertEquals( true, KingdomModelHelpersMethods.isEmpty(kingdom) );
    }

    @Test
    public void kingdomNameIsFilled(){
        Kingdom kingdom = new Kingdom( "kingdomNameExample" );
        assertEquals( false, KingdomModelHelpersMethods.isEmpty( kingdom ) );
    }
}
