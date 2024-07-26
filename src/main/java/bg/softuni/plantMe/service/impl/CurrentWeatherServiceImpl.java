package bg.softuni.plantMe.service.impl;

import bg.softuni.plantMe.models.CurrentWeather;
import bg.softuni.plantMe.models.DTOs.CurrentWeatherDTO;
import bg.softuni.plantMe.repository.CurrentWeatherRepository;
import bg.softuni.plantMe.service.CurrentWeatherService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class CurrentWeatherServiceImpl implements CurrentWeatherService {

    private final RestClient restClient;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final CurrentWeatherRepository currentWeatherRepository;
    public CurrentWeatherServiceImpl(@Qualifier("genericRestClient") RestClient restClient, Gson gson, ModelMapper modelMapper, CurrentWeatherRepository currentWeatherRepository) {
        this.restClient = restClient;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.currentWeatherRepository = currentWeatherRepository;
    }

    @Override
    public CurrentWeatherDTO getCurrentWeather() {
        String requestBody = restClient.get()
                .uri("https://api.open-meteo.com/v1/forecast?latitude=42.15&longitude=24.75&current=temperature_2m,rain,wind_speed_10m")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(String.class);

        if(requestBody != null) {
            String[] split = requestBody.split("\"current\":");
            String neededJson = split[1].substring(0, split[1].length() - 1);
            JsonObject jsonObject = JsonParser.parseString(neededJson).getAsJsonObject();
            return gson.fromJson(jsonObject, CurrentWeatherDTO.class);
        }
        return null;
    }

    public CurrentWeather map (CurrentWeatherDTO currentWeatherDTO) {
        CurrentWeather currentWeather = modelMapper.map(currentWeatherDTO, CurrentWeather.class);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        currentWeather.setTime(LocalDateTime.parse(getCurrentWeather().getTime(),dateTimeFormatter));

        return currentWeather;
    }

    public void saveCurrentWeather (CurrentWeather currentWeather) {
        currentWeatherRepository.save(currentWeather);
    }

    @Override
    public boolean isInitializedWeather() {
        return currentWeatherRepository.count() > 0;
    }


    public void fetchCurrentWeather () {

       CurrentWeather currentWeather = map(getCurrentWeather());

       if(!isInitializedWeather()) {
           currentWeatherRepository.save(currentWeather);
           return;
       }
        Optional<CurrentWeather> optional = currentWeatherRepository.findAll().stream().findFirst();
        CurrentWeather weather1 = optional.orElseThrow();

        weather1.setTime(currentWeather.getTime());
        weather1.setRain(currentWeather.getRain());
        weather1.setWindSpeed(currentWeather.getWindSpeed());
        weather1.setTemperature(currentWeather.getTemperature());

        currentWeatherRepository.save(weather1);
    }
    @Scheduled (fixedDelay = 10000000)
    public void updateCurrentWeather () {
        fetchCurrentWeather();
        System.out.println("Weather updated" + LocalDateTime.now());
    }
}
