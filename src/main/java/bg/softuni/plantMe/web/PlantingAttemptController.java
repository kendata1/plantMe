package bg.softuni.plantMe.web;

import bg.softuni.plantMe.models.DTOs.PlantingAttemptDTO;
import bg.softuni.plantMe.service.PlantService;
import bg.softuni.plantMe.service.PlantingAttemptService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/attempts")
public class PlantingAttemptController {
    private final PlantService plantService;
    private final PlantingAttemptService plantingAttemptService;

    public PlantingAttemptController(PlantService plantService, PlantingAttemptService plantingAttemptService) {
        this.plantService = plantService;
        this.plantingAttemptService = plantingAttemptService;
    }

    @ModelAttribute("plantingAttemptDTO")
    public PlantingAttemptDTO PlantingAttemptDTO() {
        return new PlantingAttemptDTO();
    }
    @ModelAttribute("plantNames")
    public List<String> plantNames() {
        return plantService.getAllPlantNames();
    }
    @GetMapping("/add")
    public String viewAddPlantingAttempt () {
        return "add-planting-attempt";
    }

    @PostMapping("add")
    public String addPlantAttempt (@Valid PlantingAttemptDTO plantingAttemptDTO,
                                   @AuthenticationPrincipal UserDetails userDetails,
                                   BindingResult bindingResult,
                                   RedirectAttributes rAtt) {

        if(bindingResult.hasErrors()){
            rAtt.addFlashAttribute("addOfferDTO", plantingAttemptDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.PlantingAttemptDTO", bindingResult);
            return "redirect:/attempts/add";
        }
        plantingAttemptDTO.setUsername(userDetails.getUsername());
        plantingAttemptService.addPlantingAttempt(plantingAttemptDTO);
        return "redirect:/home";
    }

    @GetMapping("/all/{username}")
    public String getAllAttemptsForUser (@PathVariable("username") String username, Model model) {
        model.addAttribute("plantAttemptsForUser", plantingAttemptService.getAllPlantingAttemptsShortForUser(username));

        return "user-planting-attempts";
    }

    @GetMapping("/{id}")
    public String getAttemptDetails (@PathVariable("id") Long id, Model model) {
        PlantingAttemptDTO attemptById = plantingAttemptService.getPlantingAttemptById(id);
        model.addAttribute("currentAttempt", attemptById);
        model.addAttribute("forImg", plantingAttemptService.mapToShortInfo(attemptById));

        return "planting-attempt-details";
    }

}
