package tiimae.tiimaebot.backendtiimaebot.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuildDTO {

    private String name;
    private long guildId;

    private String[] userGuildIds;
    private String settingsId;

}
