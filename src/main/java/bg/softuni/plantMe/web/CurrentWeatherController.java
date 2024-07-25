package bg.softuni.plantMe.web;

import bg.softuni.plantMe.models.DTOs.CurrentWeatherDTO;
import bg.softuni.plantMe.service.CurrentWeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrentWeatherController {
    private final CurrentWeatherService currentWeatherService;

    public CurrentWeatherController(CurrentWeatherService currentWeatherService) {
        this.currentWeatherService = currentWeatherService;
    }

    @GetMapping("/home-current-weather")
    public CurrentWeatherDTO currentWeather () {
        return currentWeatherService.getCurrentWeather();
    }

}
