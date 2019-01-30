package com.tribesbackend.tribes.services.userservice;

        import com.tribesbackend.tribes.models.TribesUser;
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
