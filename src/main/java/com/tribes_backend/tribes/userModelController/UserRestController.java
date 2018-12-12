package com.tribes_backend.tribes.userModelController;


import com.tribes_backend.tribes.repository.UserTRepository;
import com.tribes_backend.tribes.userErrorService.ErrorMessagesFunctions;
import com.tribes_backend.tribes.userModel.TribesUser;
import com.tribes_backend.tribes.userModel.UserModelHelpersMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.ErrorManager;

@RestController
public class UserRestController {
    UserTRepository userTRepository;
    UserModelHelpersMethods userMethods;
    ErrorMessagesFunctions errorMessages;

    @Autowired
    public UserRestController(UserTRepository userTRepository, UserModelHelpersMethods userMethods, ErrorMessagesFunctions errorMessages) {
        this.userTRepository = userTRepository;
        this.userMethods = userMethods;
        this.errorMessages = errorMessages;
    }




    @PostMapping(value = "/register")
    public ResponseEntity registerUser (@RequestBody TribesUser newUser){
        if (newUser.getUsername().)

    }
}
