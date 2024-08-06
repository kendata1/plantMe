package bg.softuni.plantMe.service.impl;

import bg.softuni.plantMe.config.CurrentWeatherApiConfig;
import bg.softuni.plantMe.models.DTOs.CurrentWeatherDTO;
import bg.softuni.plantMe.service.CurrentWeatherService;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.maciejwalkowiak.wiremock.spring.ConfigureWireMock;
import com.maciejwalkowiak.wiremock.spring.EnableWireMock;
import com.maciejwalkowiak.wiremock.spring.InjectWireMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;

@SpringBootTest
@EnableWireMock(
        @ConfigureWireMock(name = "current-weather-service")
)
public class CurrentWeatherServiceImplIT {

    @InjectWireMock("current-weather-service")
    private WireMockServer wireMockServer;

    @Autowired
    private CurrentWeatherService currentWeatherService;

    @Autowired
    private CurrentWeatherApiConfig currentWeatherApiConfig;



    @BeforeEach
    void setUp() {
        currentWeatherApiConfig.setUrl(wireMockServer.baseUrl() + "/test-currencies");


    }

    @Test
    void testFetchCurrentWeather() {
        wireMockServer.stubFor(get("/test-currencies").willReturn(
                        aResponse()
                                .withHeader("Content-Type", "application/json")
                                .withBody("""
                                        {"latitude":42.125,"longitude":24.75,"generationtime_ms":0.03409385681152344,"utc_offset_seconds":0,"timezone":"GMT","timezone_abbreviation":"GMT","elevation":166.0,"current_units":{"time":"iso8601","interval":"seconds","temperature_2m":"°C","is_day":"","rain":"mm","cloud_cover":"%","wind_speed_10m":"km/h"},"current":{"time":"2024-08-06T09:30","interval":900,"temperature_2m":31.4,"is_day":1,"rain":0.00,"cloud_cover":15,"wind_speed_10m":16.0}}"""


                                )
                )
        );

        CurrentWeatherDTO currentWeatherDTO = currentWeatherService.fetchCurrentWeather();

        Assertions.assertEquals("2024-08-06T09:30", currentWeatherDTO.getTime());
        Assertions.assertEquals(31.4, currentWeatherDTO.getTemperature());
        Assertions.assertEquals(1, currentWeatherDTO.getIsDay());
        Assertions.assertEquals(15.0, currentWeatherDTO.getCloudCover());
        Assertions.assertEquals(0.00, currentWeatherDTO.getRain());
        Assertions.assertEquals(16.0, currentWeatherDTO.getWindSpeed());
        Assertions.assertEquals(900, currentWeatherDTO.getInterval());
        Assertions.assertNull(currentWeatherDTO.getImageUrl());
    }


}
