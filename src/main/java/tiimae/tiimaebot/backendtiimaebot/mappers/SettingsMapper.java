package tiimae.tiimaebot.backendtiimaebot.mappers;

import org.springframework.stereotype.Component;
import tiimae.tiimaebot.backendtiimaebot.DAO.GuildDAO;
import tiimae.tiimaebot.backendtiimaebot.DTO.SettingsDTO;
import tiimae.tiimaebot.backendtiimaebot.models.Guild;
import tiimae.tiimaebot.backendtiimaebot.models.Settings;

@Component
public class SettingsMapper {

    private GuildDAO guildDAO;

    public SettingsMapper(GuildDAO guildDAO) {
        this.guildDAO = guildDAO;
    }

    public Settings toSetting(SettingsDTO settingsDTO) {

        String key = settingsDTO.getKey();
        String value = settingsDTO.getValue();

        Guild guild = null;
        if (settingsDTO.getGuildId() != null) {
            final Guild guildEntry = this.guildDAO.getGuildById(settingsDTO.getGuildId());

            guild = guildEntry;
        }

        return new Settings(guild, key, value);
    }

}
