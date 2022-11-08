package tiimae.tiimaebot.backendtiimaebot.DAO.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tiimae.tiimaebot.backendtiimaebot.models.Guild;
import tiimae.tiimaebot.backendtiimaebot.models.UserGuild;

import java.util.UUID;

public interface UserGuildRepository extends JpaRepository<UserGuild, UUID> {
}
