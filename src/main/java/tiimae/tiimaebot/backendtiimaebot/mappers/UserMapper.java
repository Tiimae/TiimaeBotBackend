package tiimae.tiimaebot.backendtiimaebot.mappers;

import org.springframework.stereotype.Component;
import tiimae.tiimaebot.backendtiimaebot.DTO.UserDTO;
import tiimae.tiimaebot.backendtiimaebot.models.User;
import tiimae.tiimaebot.backendtiimaebot.models.UserGuild;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Component
public class UserMapper {

    public User toUser(UserDTO userDTO) {
        String name = userDTO.getName();
        long userId = userDTO.getUserId();
        Set<UserGuild> userGuilds = new HashSet<>();

        return new User(name, userId, userGuilds);
    }

}
