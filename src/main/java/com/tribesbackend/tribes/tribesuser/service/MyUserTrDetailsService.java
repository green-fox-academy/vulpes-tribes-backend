package com.tribesbackend.tribes.tribesuser.service;

import com.tribesbackend.tribes.tribesuser.model.TribesUser;
import com.tribesbackend.tribes.repositories.UserTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserTrDetailsService implements UserDetailsService {

    @Autowired
    private UserTRepository userTRepository;

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<TribesUser> tribesUser = userTRepository.findTribesUserByUsername(username);
        if (tribesUser.isPresent()) {
            return tribesUser.get();
        } else throw new UsernameNotFoundException(username);
    }
}
