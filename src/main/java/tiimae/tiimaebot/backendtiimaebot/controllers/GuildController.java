package tiimae.tiimaebot.backendtiimaebot.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tiimae.tiimaebot.backendtiimaebot.DAO.GuildDAO;
import tiimae.tiimaebot.backendtiimaebot.DTO.GuildDTO;
import tiimae.tiimaebot.backendtiimaebot.Settings;
import tiimae.tiimaebot.backendtiimaebot.models.Guild;
import tiimae.tiimaebot.backendtiimaebot.response.ApiResponse;


@Controller
@RequestMapping(value = Settings.defaultApiUrl + "guild")
public class GuildController {

    private GuildDAO guildDAO;

    public GuildController(GuildDAO guildDAO) {
        this.guildDAO = guildDAO;
    }

    @PostMapping("")
    @ResponseBody
    public ApiResponse<Guild> add(@RequestBody GuildDTO guildDTO) {
        return new ApiResponse(HttpStatus.ACCEPTED, this.guildDAO.createGuildIfDontExist(guildDTO));
    }
}
