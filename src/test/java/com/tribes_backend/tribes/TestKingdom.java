package com.tribes_backend.tribes;

import com.tribes_backend.tribes.model.Kingdom;
import com.tribes_backend.tribes.model.TribesUser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestKingdom {
    Kingdom kingdom;
    String name;

    @Test
    public void testKingdomNameIsNotValid() {

        name = null;
        if (kingdom.getName().equals( null )) {
            try {

            } catch (Exception e) {

            }
        } else {
            assertEquals( name, kingdom.getName() );
        }
    }
}
