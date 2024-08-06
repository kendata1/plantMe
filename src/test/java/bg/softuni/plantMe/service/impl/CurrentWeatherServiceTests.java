package bg.softuni.plantMe.service.impl;

import bg.softuni.plantMe.config.CurrentWeatherApiConfig;
import bg.softuni.plantMe.models.CurrentWeather;
import bg.softuni.plantMe.models.DTOs.CurrentWeatherDTO;
import bg.softuni.plantMe.repository.CurrentWeatherRepository;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CurrentWeatherServiceTests {
    @Mock
    private RestClient mockRestClient;
    @Mock
    private CurrentWeatherRepository mockCurrentWeatherRepository;
    @Mock
    private  CurrentWeatherApiConfig mockCurrentWeatherApiConfig;

    private CurrentWeatherServiceImpl toTest;

    private CurrentWeatherDTO testCurrentWeatherDTO;
    @BeforeEach
    void setUp() {
        toTest = new CurrentWeatherServiceImpl(mockRestClient,
                new Gson(),
                new ModelMapper(),
                mockCurrentWeatherRepository,
                mockCurrentWeatherApiConfig);


        testCurrentWeatherDTO = new CurrentWeatherDTO();
        testCurrentWeatherDTO.setIsDay(1);
        testCurrentWeatherDTO.setRain(0.1);
        testCurrentWeatherDTO.setCloudCover(0);
    }



    @Test
    void testChoseWeatherImage_Day_Clear () {
        String chosenWeatherImage = toTest.choseWeatherImage(testCurrentWeatherDTO);
        Assertions.assertEquals("/images/weather/day_clear.png", chosenWeatherImage);
    }

    @Test
    void testChoseWeatherImage_Day_Cloudy () {
        testCurrentWeatherDTO.setCloudCover(51);
        String chosenWeatherImage = toTest.choseWeatherImage(testCurrentWeatherDTO);
        Assertions.assertEquals("/images/weather/day_cloudy.png", chosenWeatherImage);
    }
    @Test
    void testChoseWeatherImage_Day_Rain () {
        testCurrentWeatherDTO.setRain(0.7);
        String chosenWeatherImage = toTest.choseWeatherImage(testCurrentWeatherDTO);
        Assertions.assertEquals("/images/weather/day_rain.png", chosenWeatherImage);
    }
    @Test
    void testChoseWeatherImage_Night_Clear () {
        testCurrentWeatherDTO.setIsDay(0);
        String chosenWeatherImage = toTest.choseWeatherImage(testCurrentWeatherDTO);
        Assertions.assertEquals("/images/weather/night_clear.png", chosenWeatherImage);
    }

    @Test
    void testChoseWeatherImage_Night_Cloudy () {
        testCurrentWeatherDTO.setIsDay(0);
        testCurrentWeatherDTO.setCloudCover(51);
        String chosenWeatherImage = toTest.choseWeatherImage(testCurrentWeatherDTO);
        Assertions.assertEquals("/images/weather/night_cloudy.png", chosenWeatherImage);
    }
    @Test
    void testChoseWeatherImage_Night_Rain () {
        testCurrentWeatherDTO.setIsDay(0);
        testCurrentWeatherDTO.setRain(0.7);
        String chosenWeatherImage = toTest.choseWeatherImage(testCurrentWeatherDTO);
        Assertions.assertEquals("/images/weather/night_rain.png", chosenWeatherImage);
    }

    @Test
    void testGetCurrentWeather () {
        CurrentWeather testCurrentWeather = new CurrentWeather();
        testCurrentWeather.setCloudCover(50);
        testCurrentWeather.setTemperature(30.5);
        testCurrentWeather.setWindSpeed(10.5);
        testCurrentWeather.setRain(0.5);
        testCurrentWeather.setTime(LocalDateTime.now());
        testCurrentWeather.setIsDay(1);

        when(mockCurrentWeatherRepository.findById(1L)).thenReturn(Optional.of(testCurrentWeather));

        CurrentWeatherDTO currentWeatherDTO = toTest.getCurrentWeather();

        Assertions.assertEquals(testCurrentWeather.getCloudCover(),currentWeatherDTO.getCloudCover());
        Assertions.assertEquals(testCurrentWeather.getTemperature(),currentWeatherDTO.getTemperature());
        Assertions.assertEquals(testCurrentWeather.getWindSpeed(),currentWeatherDTO.getWindSpeed());
        Assertions.assertEquals(testCurrentWeather.getRain() * 100.00,currentWeatherDTO.getRain());
        Assertions.assertEquals(testCurrentWeather.getIsDay(), currentWeatherDTO.getIsDay());



    }
}
