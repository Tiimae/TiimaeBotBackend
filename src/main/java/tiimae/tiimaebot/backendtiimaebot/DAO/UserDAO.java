package tiimae.tiimaebot.backendtiimaebot.DAO;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import tiimae.tiimaebot.backendtiimaebot.DAO.repo.UserRepository;
import tiimae.tiimaebot.backendtiimaebot.DTO.UserDTO;
import tiimae.tiimaebot.backendtiimaebot.mappers.UserMapper;
import tiimae.tiimaebot.backendtiimaebot.models.User;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserDAO {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private UserGuildDAO userGuildDAO;

    public UserDAO(UserRepository userRepository, @Lazy UserMapper userMapper, @Lazy UserGuildDAO userGuildDAO) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userGuildDAO = userGuildDAO;
    }

    public User getUserById(UUID userId) {
        return this.userRepository.findById(userId).get();
    }

    public User createGuildIfDontExist(UserDTO userDTO) {
        final Optional<User> user = this.userRepository.findByUserId(userDTO.getUserId());

        User savedUser = null;
        if (user.isEmpty()) {
            final User newUser = this.userMapper.toUser(userDTO);
            savedUser = this.userRepository.save(newUser);
        }

        if (savedUser != null) {
            this.userGuildDAO.createNewUserGuild(savedUser.getId(), userDTO.getGuildId());
            return savedUser;
        }

        this.userGuildDAO.createNewUserGuild(user.get().getId(), userDTO.getGuildId());

        return user.get();
    }
}
