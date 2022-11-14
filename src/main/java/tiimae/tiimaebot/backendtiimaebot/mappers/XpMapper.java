package tiimae.tiimaebot.backendtiimaebot.mappers;

import org.springframework.stereotype.Component;
import tiimae.tiimaebot.backendtiimaebot.DAO.UserGuildDAO;
import tiimae.tiimaebot.backendtiimaebot.DTO.XpDTO;
import tiimae.tiimaebot.backendtiimaebot.models.Guild;
import tiimae.tiimaebot.backendtiimaebot.models.UserGuild;
import tiimae.tiimaebot.backendtiimaebot.models.Xp;

import java.sql.Timestamp;
import java.util.Optional;

@Component
public class XpMapper {

    private UserGuildDAO userGuildDAO;

    public XpMapper(UserGuildDAO userGuildDAO) {
        this.userGuildDAO = userGuildDAO;
    }

    public Xp toXp(XpDTO xpDTO) {
        final long exp = xpDTO.getExp();
        final long level = xpDTO.getLevel();
        final Timestamp xplock = xpDTO.getXplock();

        UserGuild userGuild = null;
        if (xpDTO.getUserGuildId() != null) {
            userGuild = this.userGuildDAO.getUserGuildById(xpDTO.getUserGuildId());
        }

        return new Xp(userGuild, exp, level, xplock);
    }

    public Xp mergeXp(Xp base, Xp update) {
        base.setUserGuild(update.getUserGuild());
        base.setExp(update.getExp());
        base.setLevel(update.getLevel());
        base.setXplock(update.getXplock());

        return base;
    }
}
