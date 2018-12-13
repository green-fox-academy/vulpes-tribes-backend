package com.tribes_backend.tribes.userModelController;


import com.tribes_backend.tribes.repository.UserTRepository;
import com.tribes_backend.tribes.userErrorService.ErrorMessagesFunctions;
import com.tribes_backend.tribes.userErrorService.ErrorResponseModel;
import com.tribes_backend.tribes.userModel.TribesUser;
import com.tribes_backend.tribes.userModel.UserModelHelpersMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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
    public ResponseEntity<Object> registerUser ( @Validated  @RequestBody TribesUser newUser)  {

        if (userMethods.usernameAlreadyTaken(newUser)){
            return new ResponseEntity(errorMessages.usernameAlreadyTaken(), HttpStatus.CONFLICT);
        }
        else if (newUser.getUsername() == null || newUser.getUsername().isEmpty()){
            return new ResponseEntity(errorMessages.usernameIsEmpty(newUser), HttpStatus.BAD_REQUEST);
        }
        else userTRepository.save(newUser);
        return ResponseEntity.ok(newUser);
           // return new ResponseEntity(newUser, HttpStatus.OK);
    }
}
