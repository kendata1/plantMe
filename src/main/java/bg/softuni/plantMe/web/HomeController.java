package bg.softuni.plantMe.web;

import bg.softuni.plantMe.models.DTOs.CurrentWeatherDTO;
import bg.softuni.plantMe.service.CurrentWeatherService;
import bg.softuni.plantMe.service.PlantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final PlantService plantService;
    private final CurrentWeatherService currentWeatherService;

    public HomeController(PlantService plantService, CurrentWeatherService currentWeatherService) {
        this.plantService = plantService;
        this.currentWeatherService = currentWeatherService;
    }

    @GetMapping("/")
    public String homeNotAuth (){
        return "index";
    }

    @GetMapping("/home")
    public String homeAuth(Model model) {
        model.addAttribute("allPlants", plantService.getAllPlantsShortInfo());
        model.addAttribute("currentWeatherDTO", currentWeatherService.getCurrentWeather());
        return "home";
    }
}
