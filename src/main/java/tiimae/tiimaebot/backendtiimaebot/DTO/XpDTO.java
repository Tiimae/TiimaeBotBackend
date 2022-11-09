package tiimae.tiimaebot.backendtiimaebot.DTO;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
public class XpDTO {

    private UUID userGuildId;
    private long exp;
    private long level;
    private Timestamp xplock;

}
