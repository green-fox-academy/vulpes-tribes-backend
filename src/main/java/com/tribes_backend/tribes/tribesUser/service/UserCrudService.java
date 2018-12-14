package com.tribes_backend.tribes.tribesUser.service;

        import com.tribes_backend.tribes.tribesUser.model.TribesUser;
        import com.tribes_backend.tribes.tribesUser.repository.UserTRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

@Service
public class UserCrudService {
    UserTRepository userTRepository;
    @Autowired
    public UserCrudService(UserTRepository userTRepository) {
        this.userTRepository = userTRepository;
    }

    public boolean save(TribesUser newUser) {
        userTRepository.save(newUser);
        return true;
    }
}
