package tiimae.tiimaebot.backendtiimaebot.DAO;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import tiimae.tiimaebot.backendtiimaebot.DAO.repo.GuildRepository;
import tiimae.tiimaebot.backendtiimaebot.DAO.repo.SettingsRepository;
import tiimae.tiimaebot.backendtiimaebot.DTO.SettingsDTO;
import tiimae.tiimaebot.backendtiimaebot.DTO.UserDTO;
import tiimae.tiimaebot.backendtiimaebot.mappers.SettingsMapper;
import tiimae.tiimaebot.backendtiimaebot.models.Guild;
import tiimae.tiimaebot.backendtiimaebot.models.Settings;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class SettingsDAO {

    private SettingsRepository settingsRepository;
    private SettingsMapper settingsMapper;
    private GuildRepository guildRepository;

    public SettingsDAO(SettingsRepository settingsRepository, @Lazy SettingsMapper settingsMapper, GuildRepository guildRepository) {
        this.settingsRepository = settingsRepository;
        this.settingsMapper = settingsMapper;
        this.guildRepository = guildRepository;
    }

    public Optional<Settings> getKeyFromDatabase(long guildId, String key) {
        final Optional<Guild> byGuildId = this.guildRepository.findByGuildId(guildId);
        Optional<Settings> setting = Optional.empty();

        if (!byGuildId.isEmpty()) {
            final Guild guild = byGuildId.get();
            setting = this.settingsRepository.findSettingsByGuildIdAndKey(guild.getId(), key);
        }

        return setting;
    }

    public Settings createSettingsIfDontExist(SettingsDTO settingsDTO) {
        final List<Settings> settings = this.settingsRepository.findAllSettingsByGuildId(settingsDTO.getGuildId());
        Settings savedSetting = null;
        boolean check = false;

        for (Settings setting : settings) {
            if (setting.getKey().equals(settingsDTO.getKey())) {
                check = true;
            }
        }

        if (!check) {
            final Settings newSetting = this.settingsMapper.toSetting(settingsDTO);
            savedSetting = this.settingsRepository.save(newSetting);
        }

        return savedSetting;
    }

    public List<Settings> getAllSettingsByGuildId(UUID guildId) {
        return this.settingsRepository.findAllSettingsByGuildId(guildId);
    }
}
