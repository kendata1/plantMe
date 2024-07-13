package bg.softuni.plantMe.web;

import bg.softuni.plantMe.service.PlantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/plants")
public class PlantDetailsController {

    private final PlantService plantService;

    public PlantDetailsController(PlantService plantService) {
        this.plantService = plantService;
    }

    @GetMapping("/{id}")
    public String viewPlantDetails (@PathVariable("id") Long id, Model model) {
        model.addAttribute("plantDetails", plantService.showPlantDetails(id));
        return "plant-details";
    }
}
