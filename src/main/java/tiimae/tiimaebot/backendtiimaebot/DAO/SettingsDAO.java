package tiimae.tiimaebot.backendtiimaebot.DAO;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import tiimae.tiimaebot.backendtiimaebot.DAO.repo.SettingsRepository;
import tiimae.tiimaebot.backendtiimaebot.DTO.SettingsDTO;
import tiimae.tiimaebot.backendtiimaebot.DTO.UserDTO;
import tiimae.tiimaebot.backendtiimaebot.mappers.SettingsMapper;
import tiimae.tiimaebot.backendtiimaebot.models.Guild;
import tiimae.tiimaebot.backendtiimaebot.models.Settings;

import java.util.List;
import java.util.Optional;

@Component
public class SettingsDAO {

    private SettingsRepository settingsRepository;
    private SettingsMapper settingsMapper;

    public SettingsDAO(SettingsRepository settingsRepository, @Lazy SettingsMapper settingsMapper) {
        this.settingsRepository = settingsRepository;
        this.settingsMapper = settingsMapper;
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
}
