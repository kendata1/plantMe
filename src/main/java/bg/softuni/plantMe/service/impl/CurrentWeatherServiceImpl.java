package bg.softuni.plantMe.service.impl;

import bg.softuni.plantMe.config.CurrentWeatherApiConfig;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class CurrentWeatherServiceImpl implements CurrentWeatherService {

    private final RestClient restClient;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final CurrentWeatherRepository currentWeatherRepository;
    private final CurrentWeatherApiConfig currentWeatherApiConfig;

    public CurrentWeatherServiceImpl(@Qualifier("genericRestClient") RestClient restClient, Gson gson, ModelMapper modelMapper, CurrentWeatherRepository currentWeatherRepository, CurrentWeatherApiConfig currentWeatherApiConfig) {
        this.restClient = restClient;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.currentWeatherRepository = currentWeatherRepository;
        this.currentWeatherApiConfig = currentWeatherApiConfig;
    }

    @Override
    public CurrentWeatherDTO fetchCurrentWeather() {
        String requestBody = restClient.get()
                .uri(currentWeatherApiConfig.getUrl())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(String.class);

        if (requestBody != null) {
            String[] split = requestBody.split("\"current\":");
            String neededJson = split[1].substring(0, split[1].length() - 1);
            JsonObject jsonObject = JsonParser.parseString(neededJson).getAsJsonObject();


            return gson.fromJson(jsonObject, CurrentWeatherDTO.class);
        }
        return null;
    }

    @Override
    public boolean isInitializedWeather() {
        return currentWeatherRepository.count() > 0;
    }

    public void updateCurrentWeather() {

        CurrentWeather currentWeather = map(fetchCurrentWeather());

        if (!isInitializedWeather()) {
            currentWeatherRepository.save(currentWeather);
            return;
        }
        Optional<CurrentWeather> optional = currentWeatherRepository.findAll().stream().findFirst();
        CurrentWeather weather1 = optional.orElseThrow();

        weather1.setTime(currentWeather.getTime());
        weather1.setRain(currentWeather.getRain());
        weather1.setWindSpeed(currentWeather.getWindSpeed());
        weather1.setTemperature(currentWeather.getTemperature());
        weather1.setIsDay(currentWeather.getIsDay());
        weather1.setCloudCover(currentWeather.getCloudCover());

        currentWeatherRepository.save(weather1);
    }

    @Scheduled(fixedDelay = 1000000)
    public void scheduledUpdateCurrentWeather() {
        updateCurrentWeather();
        System.out.println("Weather updated " + LocalDateTime.now());
    }

    @Override
    public CurrentWeatherDTO getCurrentWeather() {
        CurrentWeather currentWeather = currentWeatherRepository.findAll().get(0);

        CurrentWeatherDTO mapped = modelMapper.map(currentWeather, CurrentWeatherDTO.class);

        mapped.setImageUrl(choseWeatherImage(mapped));
        mapped.setRain(currentWeather.getRain() * 100.00);
        mapped.setTime(currentWeather.getTime().toString().replace("T"," "));
        return mapped;
    }

    private String choseWeatherImage(CurrentWeatherDTO currentWeatherDTO) {
        if (currentWeatherDTO.getIsDay() == 1) {
            if (currentWeatherDTO.getRain() > 0.6) {
                return "/images/weather/day_rain.png";
            }
            if (currentWeatherDTO.getCloudCover() > 50) {
                return "/images/weather/day_cloudy.png";
            }
            return "/images/weather/day_clear.png";
        }

        if (currentWeatherDTO.getRain() > 0.6) {
            return "/images/weather/night_rain.png";
        }
        if (currentWeatherDTO.getCloudCover() > 50) {
            return "/images/weather/night_cloudy.png";
        }
        return "/images/weather/night_clear.png";
    }


public CurrentWeather map(CurrentWeatherDTO currentWeatherDTO) {
    CurrentWeather currentWeather = modelMapper.map(currentWeatherDTO, CurrentWeather.class);

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    currentWeather.setTime(LocalDateTime.parse(currentWeatherDTO.getTime(), dateTimeFormatter));

    return currentWeather;
}
}
