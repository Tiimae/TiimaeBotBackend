package tiimae.tiimaebot.backendtiimaebot.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tiimae.tiimaebot.backendtiimaebot.DAO.UserDAO;
import tiimae.tiimaebot.backendtiimaebot.DTO.UserDTO;
import tiimae.tiimaebot.backendtiimaebot.StaticSettings;
import tiimae.tiimaebot.backendtiimaebot.models.User;
import tiimae.tiimaebot.backendtiimaebot.response.ApiResponse;

@Controller
@RequestMapping(value = StaticSettings.defaultApiUrl + "user")
public class UserController {

    private UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @PostMapping("")
    @ResponseBody
    public ApiResponse<User> add(@RequestBody UserDTO userDTO) {
        return new ApiResponse(HttpStatus.ACCEPTED, this.userDAO.createUserIfDontExist(userDTO));
    }
}
