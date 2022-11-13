package tiimae.tiimaebot.backendtiimaebot.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tiimae.tiimaebot.backendtiimaebot.DAO.XpDAO;
import tiimae.tiimaebot.backendtiimaebot.DTO.XpDTO;
import tiimae.tiimaebot.backendtiimaebot.StaticSettings;
import tiimae.tiimaebot.backendtiimaebot.models.Xp;
import tiimae.tiimaebot.backendtiimaebot.response.ApiResponse;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

@Controller
@RequestMapping(value = StaticSettings.defaultApiUrl + "xp")
public class XpController {

    private XpDAO xpDAO;

    public XpController(XpDAO xpDAO) {
        this.xpDAO = xpDAO;
    }

    @GetMapping("/{userId}/{guildId}")
    @ResponseBody
    public ApiResponse getUserXp(@PathVariable long userId, @PathVariable long guildId) {
        return new ApiResponse(HttpStatus.ACCEPTED, this.xpDAO.getUserXp(userId, guildId));
    }

    @PutMapping("/{xpId}")
    @ResponseBody
    public ApiResponse updateUserXp(@PathVariable UUID xpId, @RequestBody XpDTO xpDTO) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(xpDTO.getXplock().getTime());
        cal.add(Calendar.MINUTE, -60);
        xpDTO.setXplock(new Timestamp(cal.getTime().getTime()));

        this.xpDAO.updateXpUser(xpId, xpDTO);

        return new ApiResponse(HttpStatus.ACCEPTED, "Xp had been updated");
    }
}
