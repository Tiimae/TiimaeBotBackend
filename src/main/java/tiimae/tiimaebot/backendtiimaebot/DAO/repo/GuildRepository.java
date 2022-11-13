package tiimae.tiimaebot.backendtiimaebot.DAO.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tiimae.tiimaebot.backendtiimaebot.models.Guild;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GuildRepository extends JpaRepository<Guild, UUID> {
    public Optional<Guild> findByGuildId(long guildId);

    @Query(value = "" +
            "SELECT x.exp, x.level, u.tag " +
            "FROM User u " +
            "LEFT JOIN UserGuild ug ON ug.user.id = u.id " +
            "LEFT JOIN Xp x ON x.userGuild.id = ug.id " +
            "WHERE ug.guild.id = ?1 " +
            "ORDER BY x.exp DESC"

    )
    public List<Object> getAllByUserByGuildIdSortedOnXp(UUID guildId);
}
