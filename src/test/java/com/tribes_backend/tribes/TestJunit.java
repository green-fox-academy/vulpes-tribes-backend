package com.tribes_backend.tribes;

import com.tribes_backend.tribes.model.Kingdom;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestJunit {
    String name = "valhala";
    Kingdom kingdom = new Kingdom (name);
    @Test
    public void testKingdomIsValid(){
        if (name != null) {
            assertEquals( name, kingdom.getName() );
        }
    }
}
