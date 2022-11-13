package tiimae.tiimaebot.backendtiimaebot.DAO.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tiimae.tiimaebot.backendtiimaebot.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    public Optional<User> findByUserId(long userId);


}
