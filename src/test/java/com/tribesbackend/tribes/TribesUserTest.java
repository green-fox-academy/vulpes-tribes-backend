package com.tribesbackend.tribes;

import com.tribesbackend.tribes.tribesuser.model.TribesUser;
import com.tribesbackend.tribes.tribesuser.model.UserModelHelpersMethods;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TribesUserTest {
    @Test
    public void allUsersFailed() {
        List<TribesUser> userlist = new ArrayList<TribesUser>();
        userlist.add(new TribesUser(null, "123458"));
        userlist.add(new TribesUser("", "123458"));
        userlist.add(new TribesUser("user", ""));
        userlist.add(new TribesUser("user", null));
        userlist.add(new TribesUser("", ""));
        userlist.add(new TribesUser(null, null));
        userlist.add(new TribesUser(null, ""));
        userlist.add(new TribesUser("", null));

        for (int i = 0; i < userlist.size(); i++) {
            assertEquals(false, UserModelHelpersMethods.isValid(userlist.get(i)));
        }
    }

    @Test
    public void userOK() {
        TribesUser tribesUser = new TribesUser("ddd", "1235");
        Assert.assertEquals(true, UserModelHelpersMethods.isValid(tribesUser));
    }



    @Test
    public void usernameMissing() {
        TribesUser tribesUser = new TribesUser(null, "1235");
        assertEquals(false, UserModelHelpersMethods.isValid(tribesUser));
    }

    @Test
    public void passwordMissing() {
        TribesUser tribesUser = new TribesUser("ddd", null);
        assertEquals(false, UserModelHelpersMethods.isValid(tribesUser));
    }

    @Test
    public void bothMissing() {
        TribesUser tribesUser = new TribesUser(null, null);
        assertEquals(false, UserModelHelpersMethods.isValid(tribesUser));
    }
}
