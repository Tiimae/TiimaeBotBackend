package tiimae.tiimaebot.backendtiimaebot.DAO;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import tiimae.tiimaebot.backendtiimaebot.DAO.repo.GuildRepository;
import tiimae.tiimaebot.backendtiimaebot.DTO.GuildDTO;
import tiimae.tiimaebot.backendtiimaebot.mappers.GuildMapper;
import tiimae.tiimaebot.backendtiimaebot.models.Guild;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class GuildDAO {

    private GuildRepository guildRepository;
    private GuildMapper guildMapper;

    public GuildDAO(GuildRepository guildRepository, @Lazy GuildMapper guildMapper) {
        this.guildRepository = guildRepository;
        this.guildMapper = guildMapper;
    }

    public Guild getGuildById(UUID guildId) {
        return this.guildRepository.findById(guildId).get();
    }

    public Guild getGuildByGuildId(long guildId) {
        return this.guildRepository.findByGuildId(guildId).get();
    }

    public List<Object> getGuildLeaderboard(long guildId) {
        final Guild guildByGuildId = this.getGuildByGuildId(guildId);

        return this.guildRepository.getAllByUserByGuildIdSortedOnXp(guildByGuildId.getId());
    }

    public Guild createGuildIfDontExist(GuildDTO guildDTO) {
        final Optional<Guild> guild = this.guildRepository.findByGuildId(guildDTO.getGuildId());
        Guild savedGuild = null;
        if (guild.isEmpty()) {
            final Guild newGuild = this.guildMapper.toGuild(guildDTO);
            savedGuild = this.guildRepository.save(newGuild);
        }

        if (savedGuild != null) {
            return savedGuild;
        }

        return guild.get();
    }
}
