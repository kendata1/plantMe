package bg.softuni.plantMe.service;

import bg.softuni.plantMe.models.CurrentWeather;
import bg.softuni.plantMe.models.DTOs.CurrentWeatherDTO;

public interface CurrentWeatherService {
    CurrentWeatherDTO fetchCurrentWeather ();
    CurrentWeather map (CurrentWeatherDTO currentWeatherDTO);
    void updateCurrentWeather ();
    boolean isInitializedWeather ();
    CurrentWeatherDTO getCurrentWeather ();
}
