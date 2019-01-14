package com.tribes_backend.tribes.tribesUser.repository;

        import com.tribes_backend.tribes.tribesUser.model.TribesUser;
        import org.springframework.data.jpa.repository.JpaRepository;

        import org.springframework.stereotype.Repository;
        import org.springframework.stereotype.Service;

        import java.util.List;
        import java.util.Optional;

@Repository
public interface UserTRepository extends JpaRepository<TribesUser, Long> {
    TribesUser findTribesUserByUsername(String username);

    @Override
    List<TribesUser> findAll();

    Optional<TribesUser> findByUsername(String username);
}
