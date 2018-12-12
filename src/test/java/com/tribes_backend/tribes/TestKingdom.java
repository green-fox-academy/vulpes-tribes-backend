package com.tribes_backend.tribes;

import com.tribes_backend.tribes.model.Kingdom;
import com.tribes_backend.tribes.model.TribesUser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestKingdom {
    String name;



    @Test
    public void testKingdomNameIsNotValid(){
        name = null;
        Kingdom kingdom = new Kingdom( name );
        assertEquals(null, kingdom.getName());
    }

    @Test
    public void testKingdomNameIsValid(){
        name = "Ondra";
        Kingdom kingdom = new Kingdom( name );
        assertEquals( "Ondra", kingdom.getName() );
    }
}
