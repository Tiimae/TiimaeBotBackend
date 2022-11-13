package tiimae.tiimaebot.backendtiimaebot.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tiimae.tiimaebot.backendtiimaebot.DAO.SettingsDAO;
import tiimae.tiimaebot.backendtiimaebot.DTO.SettingsDTO;
import tiimae.tiimaebot.backendtiimaebot.StaticSettings;
import tiimae.tiimaebot.backendtiimaebot.models.Settings;
import tiimae.tiimaebot.backendtiimaebot.response.ApiResponse;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping(value = StaticSettings.defaultApiUrl + "setting")
public class SettingsController {

    private SettingsDAO settingsDAO;

    public SettingsController(SettingsDAO settingsDAO) {
        this.settingsDAO = settingsDAO;
    }

    @GetMapping("/key/{guildId}/{key}")
    @ResponseBody
    public ApiResponse<Settings> getPrefix(@PathVariable long guildId, @PathVariable String key) {
        final Optional<Settings> keyFromDatabase = this.settingsDAO.getKeyFromDatabase(guildId, key.toUpperCase(Locale.ROOT));

        if (keyFromDatabase.isEmpty()) {
            return new ApiResponse(HttpStatus.ACCEPTED, "Key not found");
        } else {
            return new ApiResponse(HttpStatus.ACCEPTED, keyFromDatabase.get());
        }
    }

    @PostMapping("")
    @ResponseBody
    public ApiResponse<Settings> add(@RequestBody SettingsDTO settingsDTO) {
        return new ApiResponse(HttpStatus.ACCEPTED, this.settingsDAO.createSettingsIfDontExist(settingsDTO));
    }

    @GetMapping("/all/{guildId}")
    @ResponseBody
    public ApiResponse<List<Settings>> getAllGuildSettings(@PathVariable UUID guildId) {
        return new ApiResponse(HttpStatus.ACCEPTED, this.settingsDAO.getAllSettingsByGuildId(guildId));
    }
}
