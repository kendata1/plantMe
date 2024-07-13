package bg.softuni.plantMe.web;

import bg.softuni.plantMe.service.PlantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final PlantService plantService;

    public HomeController(PlantService plantService) {
        this.plantService = plantService;
    }

    @GetMapping("/")
    public String homeNotAuth (){
        return "index";
    }

    @GetMapping("/home")
    public String homeAuth(Model model) {
        model.addAttribute("allPlants", plantService.getAllPlantsShortInfo());
        return "home";
    }
}
