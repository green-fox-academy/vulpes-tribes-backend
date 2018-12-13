package com.tribes_backend.tribes;

import com.tribes_backend.tribes.userModel.TribesUser;
import com.tribes_backend.tribes.userModel.UserModelHelpersMethods;
import org.junit.Test;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;


public class TribesUserTest {



    @Test
    public void userOK(){
        TribesUser tribesUser = new TribesUser("ddd", "1235");
        UserModelHelpersMethods userHelp = new UserModelHelpersMethods();
        assertEquals(true, userHelp.isValid(tribesUser));
    }
    @Test
    public void usernameMissing(){
        TribesUser tribesUser = new TribesUser(null, "1235");
        UserModelHelpersMethods userHelp = new UserModelHelpersMethods();
        assertEquals(false, userHelp.isValid(tribesUser));
    }

    @Test
    public void passwordMissing(){
        TribesUser tribesUser = new TribesUser("ddd", null);
        UserModelHelpersMethods userHelp = new UserModelHelpersMethods();
        assertEquals(false, userHelp.isValid(tribesUser));
    }
    @Test
    public void bothMissing(){
        TribesUser tribesUser = new TribesUser(null, null);
        UserModelHelpersMethods userHelp = new UserModelHelpersMethods();
        assertEquals(false, userHelp.isValid(tribesUser));
    }

}
