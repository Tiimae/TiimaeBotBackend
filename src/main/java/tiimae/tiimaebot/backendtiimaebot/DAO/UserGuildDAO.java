package tiimae.tiimaebot.backendtiimaebot.DAO;

import org.springframework.stereotype.Component;
import tiimae.tiimaebot.backendtiimaebot.DAO.repo.UserGuildRepository;
import tiimae.tiimaebot.backendtiimaebot.mappers.UserGuildMapper;
import tiimae.tiimaebot.backendtiimaebot.models.UserGuild;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserGuildDAO {

    private UserGuildRepository userGuildRepository;
    private UserGuildMapper userGuildMapper;

    public UserGuildDAO(UserGuildRepository userGuildRepository, UserGuildMapper userGuildMapper) {
        this.userGuildRepository = userGuildRepository;
        this.userGuildMapper = userGuildMapper;
    }

    public UserGuild getUserGuildById(UUID userGuildId) {
        return this.userGuildRepository.getById(userGuildId);
    }

    public void createNewUserGuild(UUID userId, UUID guildId) {
        final Optional<UUID> userGuildByIds = this.getUserGuildByUserIdAndGuildId(userId, guildId);

        if (userGuildByIds.isEmpty()) {
            final UserGuild save = this.userGuildRepository.save(this.userGuildMapper.toUserGuild(userId, guildId));
            save.getXp().setUserGuild(save);
            this.userGuildRepository.saveAndFlush(save);
        }
    }

    public Optional<UUID> getUserGuildByUserIdAndGuildId(UUID userId, UUID guildId) {
        return this.userGuildRepository.findByUserIdAndGuildId(userId, guildId);
    }
}
