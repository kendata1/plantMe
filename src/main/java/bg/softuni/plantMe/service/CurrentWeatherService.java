package bg.softuni.plantMe.service;

import bg.softuni.plantMe.models.CurrentWeather;
import bg.softuni.plantMe.models.DTOs.CurrentWeatherDTO;

public interface CurrentWeatherService {
    CurrentWeatherDTO getCurrentWeather ();
    public CurrentWeather map (CurrentWeatherDTO currentWeatherDTO);
    public void saveCurrentWeather (CurrentWeather currentWeather);
    public void fetchCurrentWeather ();
    public boolean isInitializedWeather ();
}
