package tiimae.tiimaebot.backendtiimaebot.DAO.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tiimae.tiimaebot.backendtiimaebot.models.Settings;
import tiimae.tiimaebot.backendtiimaebot.models.UserGuild;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SettingsRepository extends JpaRepository<Settings, UUID> {
    public List<Settings> findAllSettingsByGuildId(UUID guildId);


    public Optional<Settings> findSettingsByGuildIdAndKey(UUID guildId, String key);
}
