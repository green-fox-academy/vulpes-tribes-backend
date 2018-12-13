package com.tribes_backend.tribes.userModelController;


import com.tribes_backend.tribes.repository.UserTRepository;
import com.tribes_backend.tribes.userModel.TribesUser;
import com.tribes_backend.tribes.userModel.UserModelHelpersMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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


//    @PostMapping(value = "/login")
//
//    public ResponseEntity loginUser(@RequestBody TribesUser tribesUser) {
//        if (userMethods.isValid(tribesUser)) {
//            if (userTRepository.findTribesUserByUsername(tribesUser.getUsername()).getPassword() ==
//                    tribesUser.getPassword()){
//                return new ResponseEntity(,HttpStatus.OK)
//            }
//
//
//        }
//
//    }
}
