package com.tribes_backend.tribes;

import com.tribes_backend.tribes.tribesUser.model.TribesUser;
import com.tribes_backend.tribes.tribesUser.model.UserModelHelpersMethods;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TribesUserTest {



    @Test
    public void userOK(){
        TribesUser tribesUser = new TribesUser("ddd", "1235");
        assertEquals(true, UserModelHelpersMethods.isValid(tribesUser));
    }
    @Test
    public void usernameMissing(){
        TribesUser tribesUser = new TribesUser(null, "1235");
        assertEquals(false, UserModelHelpersMethods.isValid(tribesUser));
    }

    @Test
    public void passwordMissing(){
        TribesUser tribesUser = new TribesUser("ddd", null);
        assertEquals(false, UserModelHelpersMethods.isValid(tribesUser));
    }
    @Test
    public void bothMissing(){
        TribesUser tribesUser = new TribesUser(null, null);
        assertEquals(false, UserModelHelpersMethods.isValid(tribesUser));
    }

}
