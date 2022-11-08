package tiimae.tiimaebot.backendtiimaebot.DAO;

import org.springframework.stereotype.Component;
import tiimae.tiimaebot.backendtiimaebot.DAO.repo.UserGuildRepository;
import tiimae.tiimaebot.backendtiimaebot.mappers.UserGuildMapper;
import tiimae.tiimaebot.backendtiimaebot.models.UserGuild;

import java.util.UUID;

@Component
public class UserGuildDAO {

    private UserGuildRepository userGuildRepository;
    private UserGuildMapper userGuildMapper;

    public UserGuildDAO(UserGuildRepository userGuildRepository, UserGuildMapper userGuildMapper) {
        this.userGuildRepository = userGuildRepository;
        this.userGuildMapper = userGuildMapper;
    }

    public void createNewUserGuild(UUID userId, UUID guildId) {
        final UserGuild save = this.userGuildRepository.save(this.userGuildMapper.toUserGuild(userId, guildId));
        save.getXp().setUserGuild(save);
        this.userGuildRepository.saveAndFlush(save);
    }
}
