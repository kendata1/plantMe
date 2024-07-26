package bg.softuni.plantMe.web;

import bg.softuni.plantMe.models.DTOs.AddPlantingAttemptDTO;
import bg.softuni.plantMe.service.PlantService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/planting-attempts")
public class PlantingAttemptController {
    private final PlantService plantService;

    public PlantingAttemptController(PlantService plantService) {
        this.plantService = plantService;
    }

    @ModelAttribute("addPlantingAttemptDTO")
    public AddPlantingAttemptDTO addPlantingAttemptDTO() {
        return new AddPlantingAttemptDTO();
    }
    @ModelAttribute("plantNames")
    public List<String> plantNames() {
        return plantService.getAllPlantNames();
    }
    @GetMapping("/add")
    public String viewAddPlantingAttempt () {
        return "add-planting-attempt";
    }
}
