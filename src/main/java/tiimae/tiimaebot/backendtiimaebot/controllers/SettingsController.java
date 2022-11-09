package tiimae.tiimaebot.backendtiimaebot.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tiimae.tiimaebot.backendtiimaebot.DAO.SettingsDAO;
import tiimae.tiimaebot.backendtiimaebot.DTO.SettingsDTO;
import tiimae.tiimaebot.backendtiimaebot.Settings;
import tiimae.tiimaebot.backendtiimaebot.response.ApiResponse;

@Controller
@RequestMapping(value = Settings.defaultApiUrl + "setting")
public class SettingsController {

    private SettingsDAO settingsDAO;

    public SettingsController(SettingsDAO settingsDAO) {
        this.settingsDAO = settingsDAO;
    }

    @PostMapping("")
    @ResponseBody
    public ApiResponse<Settings> add(@RequestBody SettingsDTO settingsDTO) {
        return new ApiResponse(HttpStatus.ACCEPTED, this.settingsDAO.createSettingsIfDontExist(settingsDTO));
    }
}
