package tiimae.tiimaebot.backendtiimaebot.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tiimae.tiimaebot.backendtiimaebot.DAO.GuildDAO;
import tiimae.tiimaebot.backendtiimaebot.DTO.GuildDTO;
import tiimae.tiimaebot.backendtiimaebot.StaticSettings;
import tiimae.tiimaebot.backendtiimaebot.models.Guild;
import tiimae.tiimaebot.backendtiimaebot.response.ApiResponse;

import java.util.List;


@Controller
@RequestMapping(value = StaticSettings.defaultApiUrl + "guild")
public class GuildController {

    private GuildDAO guildDAO;

    public GuildController(GuildDAO guildDAO) {
        this.guildDAO = guildDAO;
    }

    @GetMapping("/{guildId}")
    @ResponseBody
    public ApiResponse<Guild> getGuild(@PathVariable long guildId) {
        return new ApiResponse(HttpStatus.ACCEPTED, this.guildDAO.getGuildByGuildId(guildId));
    }

    @PostMapping("")
    @ResponseBody
    public ApiResponse<Guild> add(@RequestBody GuildDTO guildDTO) {
        return new ApiResponse(HttpStatus.ACCEPTED, this.guildDAO.createGuildIfDontExist(guildDTO));
    }

    @GetMapping("/leaderboard/{guildId}")
    @ResponseBody
    public ApiResponse<List<Object>> getLeaderboard(@PathVariable long guildId) {
        return new ApiResponse(HttpStatus.ACCEPTED, this.guildDAO.getGuildLeaderboard(guildId));
    }
}
