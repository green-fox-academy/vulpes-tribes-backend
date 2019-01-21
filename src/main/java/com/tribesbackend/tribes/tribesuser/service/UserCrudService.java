package com.tribesbackend.tribes.tribesuser.service;

        import com.tribesbackend.tribes.tribesuser.model.TribesUser;
        import com.tribesbackend.tribes.repositories.UserTRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

@Service
public class UserCrudService {
    UserTRepository userTRepository;
    @Autowired
    public UserCrudService(UserTRepository userTRepository) {
        this.userTRepository = userTRepository;
    }

    public void save(TribesUser newUser) throws Exception{
        userTRepository.save(newUser);
    }
}
