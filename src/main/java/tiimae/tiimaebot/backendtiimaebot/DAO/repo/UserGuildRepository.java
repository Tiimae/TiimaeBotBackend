package tiimae.tiimaebot.backendtiimaebot.DAO.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tiimae.tiimaebot.backendtiimaebot.models.Guild;
import tiimae.tiimaebot.backendtiimaebot.models.User;
import tiimae.tiimaebot.backendtiimaebot.models.UserGuild;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserGuildRepository extends JpaRepository<UserGuild, UUID> {

    @Query(value = "SELECT ug.id FROM UserGuild ug WHERE ug.user.id = ?1 AND ug.guild.id = ?2")
    public Optional<UUID> findByUserIdAndGuildId(UUID userId, UUID guildId);
}
