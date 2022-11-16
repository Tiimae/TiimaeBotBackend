package tiimae.tiimaebot.backendtiimaebot.DTO;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class MoneyDTO {

    private int totalMoney;
    private Timestamp moneyLock;
    private Timestamp robLock;

    private String userGuildId;
}
