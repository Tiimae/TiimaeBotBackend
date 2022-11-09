package tiimae.tiimaebot.backendtiimaebot.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SettingsDTO {

    private UUID guildId;
    private String key;
    private String value;

}
