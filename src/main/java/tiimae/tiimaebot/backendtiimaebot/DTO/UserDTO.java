package tiimae.tiimaebot.backendtiimaebot.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDTO {

    private String name;
    private long userId;
    private String discriminator;
    private String tag;

    private String[] userGuildIds;
    private UUID guildId;
}
