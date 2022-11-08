package tiimae.tiimaebot.backendtiimaebot.DAO.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tiimae.tiimaebot.backendtiimaebot.models.Guild;

import java.util.Optional;
import java.util.UUID;

public interface GuildRepository extends JpaRepository<Guild, UUID> {
    public Optional<Guild> findByGuildId(long guildId);
}
