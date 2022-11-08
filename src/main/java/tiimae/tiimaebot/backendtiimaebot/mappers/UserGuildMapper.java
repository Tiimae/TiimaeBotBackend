package tiimae.tiimaebot.backendtiimaebot.mappers;

import org.springframework.stereotype.Component;
import tiimae.tiimaebot.backendtiimaebot.DAO.GuildDAO;
import tiimae.tiimaebot.backendtiimaebot.DAO.UserDAO;
import tiimae.tiimaebot.backendtiimaebot.models.Guild;
import tiimae.tiimaebot.backendtiimaebot.models.User;
import tiimae.tiimaebot.backendtiimaebot.models.UserGuild;
import tiimae.tiimaebot.backendtiimaebot.models.Xp;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserGuildMapper {

    private UserDAO userDAO;
    private GuildDAO guildDAO;

    public UserGuildMapper(UserDAO userDAO, GuildDAO guildDAO) {
        this.userDAO = userDAO;
        this.guildDAO = guildDAO;
    }

    public UserGuild toUserGuild(UUID userId, UUID guildId) {

        User user = null;
        if (userId != null) {
            final User userEntry = this.userDAO.getUserById(userId);

            user = userEntry;
        }

        Guild guild = null;
        if (guildId != null) {
            final Guild guildEntry = this.guildDAO.getGuildById(guildId);

            guild = guildEntry;
        }

        return new UserGuild(user, guild, new Xp());
    }
}
