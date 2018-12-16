

import com.tribes_backend.tribes.tribesUser.errorService.ErrorMessagesMethods;
import com.tribes_backend.tribes.tribesUser.exception.InvalidUserPasswordException;
import com.tribes_backend.tribes.tribesUser.exception.TribesError;
import com.tribes_backend.tribes.tribesUser.model.TribesUser;
import com.tribes_backend.tribes.tribesUser.model.UserModelHelpersMethods;
import com.tribes_backend.tribes.tribesUser.repository.UserTRepository;
import com.tribesbackend.tribes.tribesuser.errorservice.ErrorMessagesMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserRestController {
    UserTRepository userTRepository;
    UserModelHelpersMethods userMethods;
    ErrorMessagesMethods errorMessages;

    @Autowired
    public UserRestController(UserTRepository userTRepository, UserModelHelpersMethods userMethods, ErrorMessagesMethods errorMessages) {
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
            return new ResponseEntity(errorMessages.jsonFieldIsEmpty(newUser), HttpStatus.BAD_REQUEST);
        }
        else userTRepository.save(newUser);
        return ResponseEntity.ok(newUser);
           // return new ResponseEntity(newUser, HttpStatus.OK);
    }


    @PostMapping(value = "/login")
    public ResponseEntity loginUser(@RequestBody TribesUser tribesUser) {
        if (userMethods.isValid(tribesUser)) {
            //username is in database andpassword matches
            if (userTRepository.findTribesUserByUsername(tribesUser.getUsername()).getPassword() ==
                    tribesUser.getPassword()) {
                return new ResponseEntity("token", HttpStatus.OK);
                //username is not empty, but is not in database
            } else if (userTRepository.findTribesUserByUsername(tribesUser.getUsername()) == null) {
              //  throw new InvalidUserPasswordException("error", "Not such user: " + tribesUser.getUsername());
                return new ResponseEntity(
                        new InvalidUserPasswordException("error", "Not such user: " + tribesUser.getUsername())
                        ,HttpStatus.UNAUTHORIZED);
            } else if (userTRepository.findTribesUserByUsername(tribesUser.getUsername()).getPassword() !=
                    tribesUser.getPassword()) {
           //     throw new InvalidUserPasswordException("error", "Wrong password!");
                return  new ResponseEntity(
                        new InvalidUserPasswordException("error", "Wrong password!")
                        ,HttpStatus.UNAUTHORIZED);
            }
        }
        //empty username or empty password or empty both
     //   else {
            String textError;
            if (tribesUser.getUsername() == null && tribesUser.getPassword() == null) {
                textError = "username, password";
            } else if (tribesUser.getUsername() == null) {
                textError = "username";
            } else {
                textError = "password";
            }
        //    throw new InvalidUserPasswordException("error", textError);
            return  new ResponseEntity(
                    new InvalidUserPasswordException("error", textError), HttpStatus.BAD_REQUEST);
     //   }
     //   return
    }

}
