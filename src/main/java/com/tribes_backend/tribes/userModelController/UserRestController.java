package com.tribes_backend.tribes.userModelController;


import com.tribes_backend.tribes.repository.UserTRepository;
import com.tribes_backend.tribes.userModel.UserModelHelpersMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {
    UserTRepository userTRepository;
    UserModelHelpersMethods userMethods;

    @Autowired
    public UserRestController(UserTRepository userTRepository, UserModelHelpersMethods userMethods) {
        this.userTRepository = userTRepository;
        this.userMethods = userMethods;
    }
}
