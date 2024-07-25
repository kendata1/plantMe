package bg.softuni.plantMe.init;


import bg.softuni.plantMe.service.CurrentWeatherService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CurrentWeatherInitializer implements CommandLineRunner {
    private final CurrentWeatherService currentWeatherService;

    public CurrentWeatherInitializer(CurrentWeatherService currentWeatherService) {
        this.currentWeatherService = currentWeatherService;
    }

    @Override
    public void run(String... args) throws Exception {
        currentWeatherService.fetchCurrentWeather();
    }
}
