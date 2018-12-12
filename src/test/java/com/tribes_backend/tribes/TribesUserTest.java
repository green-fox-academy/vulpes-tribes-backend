package com.tribes_backend.tribes;

import com.tribes_backend.tribes.userModel.TribesUser;
import com.tribes_backend.tribes.userModel.UserModelHelpersMethods;
import org.junit.Test;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;


public class TribesUserTest {
String username;
String password;



    @Test
    public void userOK(){
        username = "ddd";
        password = "123456789";
        UserModelHelpersMethods userHelp = new UserModelHelpersMethods();
        boolean result = userHelp.isValid(username, password);
        assertEquals(true, userHelp.isValid(username, password));
    }
    @Test
    public void usernameMissing(){
        username = null;
        password = "123456789";
        UserModelHelpersMethods userHelp = new UserModelHelpersMethods();
        boolean result = userHelp.isValid(username, password);
        assertEquals(false, userHelp.isValid(username, password));
    }

    @Test
    public void passwordMissing(){
        username = "ddd";
        password = null;
        UserModelHelpersMethods userHelp = new UserModelHelpersMethods();
        boolean result = userHelp.isValid(username, password);
        assertEquals(false, userHelp.isValid(username, password));
    }
    @Test
    public void bothMissing(){
        username = null;
        password = null;
        UserModelHelpersMethods userHelp = new UserModelHelpersMethods();
        boolean result = userHelp.isValid(username, password);
        assertEquals(false, userHelp.isValid(username, password));
    }

}
