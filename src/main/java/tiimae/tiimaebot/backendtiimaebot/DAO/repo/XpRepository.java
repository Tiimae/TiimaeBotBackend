package tiimae.tiimaebot.backendtiimaebot.DAO.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tiimae.tiimaebot.backendtiimaebot.models.Settings;
import tiimae.tiimaebot.backendtiimaebot.models.Xp;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface XpRepository extends JpaRepository<Xp, UUID> {

    public Optional<Xp> findXpByUserGuildId(UUID userGuildId);

}
