package tiimae.tiimaebot.backendtiimaebot.DAO;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import tiimae.tiimaebot.backendtiimaebot.DAO.repo.XpRepository;
import tiimae.tiimaebot.backendtiimaebot.DTO.XpDTO;
import tiimae.tiimaebot.backendtiimaebot.mappers.XpMapper;
import tiimae.tiimaebot.backendtiimaebot.models.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public class XpDAO {

    private XpRepository xpRepository;
    private XpMapper xpMapper;
    private UserGuildDAO userGuildDAO;
    private UserDAO userDAO;
    private GuildDAO guildDAO;

    public XpDAO(XpRepository xpRepository, XpMapper xpMapper, UserGuildDAO userGuildDAO, UserDAO userDAO, GuildDAO guildDAO) {
        this.xpRepository = xpRepository;
        this.xpMapper = xpMapper;
        this.userGuildDAO = userGuildDAO;
        this.userDAO = userDAO;
        this.guildDAO = guildDAO;
    }

    public Map getUserXp(long userId, long guildId) {
        final User userByUserId = this.userDAO.getUserByUserId(userId);
        final UUID userIdUUID = userByUserId.getId();

        final Guild guildByGuildId = this.guildDAO.getGuildByGuildId(guildId);
        final UUID guildIdUUID = guildByGuildId.getId();

        final Optional<UUID> userGuildOptional = this.userGuildDAO.getUserGuildByUserIdAndGuildId(userIdUUID, guildIdUUID);
        final UUID userGuild = userGuildOptional.get();

        final Optional<Xp> xpByUserGuildId = this.xpRepository.findXpByUserGuildId(userGuild);

        final Xp xp = xpByUserGuildId.get();

        Map payload = new HashMap();
        payload.put("xp", xp);
        payload.put("xplock", xp.getXplock().getTime());
        payload.put("userGuildId", userGuild);

        return payload;
    }

    public void updateXpUser(UUID xpId, XpDTO xpDTO) {
        final Optional<Xp> xpOld = this.xpRepository.findById(xpId);
        final Xp updateXp = this.xpMapper.toXp(xpDTO);

        final Xp xp = this.xpMapper.mergeXp(xpOld.get(), updateXp);
        this.xpRepository.saveAndFlush(xp);
    }
}
