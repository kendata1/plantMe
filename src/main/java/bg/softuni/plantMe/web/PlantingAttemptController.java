package bg.softuni.plantMe.web;

import bg.softuni.plantMe.models.DTOs.PlantingAttemptDTO;
import bg.softuni.plantMe.service.PlantService;
import bg.softuni.plantMe.service.PlantingAttemptService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/all")
    public String getAllAttemptsForUser () {
        return null;
    }
    @PostMapping("add")
    public String addPlantAttempt (@Valid PlantingAttemptDTO plantingAttemptDTO,
                                   @AuthenticationPrincipal UserDetails userDetails,
                                   BindingResult bindingResult,
                                   RedirectAttributes rAtt) {

        if(bindingResult.hasErrors()){
            rAtt.addFlashAttribute("addOfferDTO", plantingAttemptDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.PlantingAttemptDTO", bindingResult);
            return "redirect:/";
        }
        plantingAttemptDTO.setUsername(userDetails.getUsername());
        plantingAttemptService.addPlantingAttempt(plantingAttemptDTO);
        return "redirect:/login";
    }
}
