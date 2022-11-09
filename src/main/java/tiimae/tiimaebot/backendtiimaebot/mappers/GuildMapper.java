package tiimae.tiimaebot.backendtiimaebot.mappers;

import org.springframework.stereotype.Component;
import tiimae.tiimaebot.backendtiimaebot.DTO.GuildDTO;
import tiimae.tiimaebot.backendtiimaebot.models.Guild;
import tiimae.tiimaebot.backendtiimaebot.models.Settings;
import tiimae.tiimaebot.backendtiimaebot.models.UserGuild;

import java.util.HashSet;
import java.util.Set;

@Component
public class GuildMapper {

    public Guild toGuild(GuildDTO guildDTO) {
        String name = guildDTO.getName();
        long guildid = guildDTO.getGuildId();
        Set<UserGuild> userGuilds = new HashSet<>();
        Settings settings = null;

        return new Guild(name, guildid, userGuilds, settings);
    }
}
