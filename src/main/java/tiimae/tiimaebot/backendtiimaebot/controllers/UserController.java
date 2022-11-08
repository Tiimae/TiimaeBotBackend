package tiimae.tiimaebot.backendtiimaebot.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tiimae.tiimaebot.backendtiimaebot.DAO.UserDAO;
import tiimae.tiimaebot.backendtiimaebot.DTO.UserDTO;
import tiimae.tiimaebot.backendtiimaebot.Settings;
import tiimae.tiimaebot.backendtiimaebot.models.User;
import tiimae.tiimaebot.backendtiimaebot.response.ApiResponse;

@Controller
@RequestMapping(value = Settings.defaultApiUrl + "user")
public class UserController {

    private UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @PostMapping("")
    @ResponseBody
    public ApiResponse<User> addUser(@RequestBody UserDTO userDTO) {
        return new ApiResponse(HttpStatus.ACCEPTED, this.userDAO.createGuildIfDontExist(userDTO));
    }
}
