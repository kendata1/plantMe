package bg.softuni.plantMe.service.impl;

import bg.softuni.plantMe.config.AttemptsApiConfig;
import bg.softuni.plantMe.config.CurrentWeatherApiConfig;
import bg.softuni.plantMe.models.DTOs.CurrentWeatherDTO;
import bg.softuni.plantMe.models.DTOs.PlantingAttemptDTO;
import bg.softuni.plantMe.service.CurrentWeatherService;
import bg.softuni.plantMe.service.PlantingAttemptService;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.maciejwalkowiak.wiremock.spring.ConfigureWireMock;
import com.maciejwalkowiak.wiremock.spring.EnableWireMock;
import com.maciejwalkowiak.wiremock.spring.InjectWireMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest
@EnableWireMock(
        @ConfigureWireMock(name = "attempts-service")
)
public class PlantingAttemptsServiceImplIT {

    @InjectWireMock("attempts-service")
    private WireMockServer wireMockServer;

    @Autowired
    private PlantingAttemptService plantingAttemptService;

    @Autowired
    AttemptsApiConfig attemptsApiConfig;

    @BeforeEach
    void setUp() {
        attemptsApiConfig.setBaseUrl(wireMockServer.baseUrl());
    }

    @Test
    void testGetAllPlantingAttemptsForUser() {
        wireMockServer.stubFor(get("/all/kendata").willReturn(
                        aResponse()
                                .withHeader("Content-Type", "application/json")
                                .withBody("""
                                        [{"id":1,"plant":"Tomato","username":"kendata","variety":"Стар розов","plantingDate":"2024-05-06","seedInfo":"Запазени от домати, закупени от пазара в Гагарин.","seedlingInfo":"","mainInfo":"Разсад започнат от семена на закрито в началото на Март. Развива се добре, като е подхранван няколко пъти с тор от калифорнийски червеи. Засаден навън през м. Май \\"полегнал\\".\\r\\nПоливан с капково с капкообразуватели 4л/час. В началото 30 мин. през 72ч. В жегите 90 мин. през 48 ч. Хранен с натурални торове - чай от коприва и чай от смесени растения . Проблеми с загниване в долната част на плодовете."},{"id":11,"plant":"Cucumber","username":"kendata","variety":"sdadsasdasaddsa","plantingDate":"2024-07-31","seedInfo":"saddasasdasdasdasdassa","seedlingInfo":"","mainInfo":"adsdsaasdadsasdadsadsdasasdasddasadsdsadsa"}]"""
                                )
                )
        );

        List<PlantingAttemptDTO> plantingAttemptDTOList = plantingAttemptService.getAllPlantingAttemptsForUser("kendata");

        Assertions.assertEquals(2, plantingAttemptDTOList.size());

        for (PlantingAttemptDTO plantingAttemptDTO : plantingAttemptDTOList) {
            Assertions.assertEquals(plantingAttemptDTO.getUsername(), "kendata");
        }

    }

    @Test
    void testGetAllPlantingAttemptsOfOtherUsers() {
        wireMockServer.stubFor(get("/isnt/kendata").willReturn(
                        aResponse()
                                .withHeader("Content-Type", "application/json")
                                .withBody("""
                                        [{"id":10,"plant":"Cucumber","username":"kendata123","variety":"dsadsasada","plantingDate":"2024-08-01","seedInfo":"asdadssadsad","seedlingInfo":"sadsadsdasad","mainInfo":"sadasdsadsadsadasdsadsadadsdsaasd"}]"""
                                )
                )
        );

        List<PlantingAttemptDTO> plantingAttemptDTOList = plantingAttemptService.getAllPlantingAttemptsOfOtherUsers("kendata");

        Assertions.assertEquals(1, plantingAttemptDTOList.size());

        for (PlantingAttemptDTO plantingAttemptDTO : plantingAttemptDTOList) {
            Assertions.assertNotEquals(plantingAttemptDTO.getUsername(), "kendata");
        }
    }

    @Test
    void testGetPlantingAttemptById() {
        wireMockServer.stubFor(get("/10").willReturn(
                        aResponse()
                                .withHeader("Content-Type", "application/json")
                                .withBody("""
                                        {"id":10,"plant":"Cucumber","username":"kendata123","variety":"dsadsasada","plantingDate":"2024-08-01","seedInfo":"asdadssadsad","seedlingInfo":"sadsadsdasad","mainInfo":"sadasdsadsadsadasdsadsadadsdsaasd"}"""
                                )
                )
        );

        PlantingAttemptDTO plantingAttemptDTO = plantingAttemptService.getPlantingAttemptById(10L);

            Assertions.assertEquals(plantingAttemptDTO.getId(), "10");
            Assertions.assertEquals(plantingAttemptDTO.getPlant(), "Cucumber");
            Assertions.assertEquals(plantingAttemptDTO.getUsername(), "kendata123");
            Assertions.assertEquals(plantingAttemptDTO.getVariety(), "dsadsasada");
            Assertions.assertEquals(plantingAttemptDTO.getPlantingDate(), "2024-08-01");
            Assertions.assertEquals(plantingAttemptDTO.getSeedInfo(), "asdadssadsad");
            Assertions.assertEquals(plantingAttemptDTO.getSeedlingInfo(), "sadsadsdasad");
            Assertions.assertEquals(plantingAttemptDTO.getMainInfo(), "sadasdsadsadsadasdsadsadadsdsaasd");
    }
}
