package bg.softuni.plantMe.service.impl;

import bg.softuni.plantMe.models.DTOs.PlantingAttemptDTO;
import bg.softuni.plantMe.service.PlantingAttemptService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
@Service
public class PlantingAttemptServiceImpl implements PlantingAttemptService {
    private final Logger LOGGER = LoggerFactory.getLogger(PlantingAttemptServiceImpl.class);
    private final RestClient attemptsRestClient;
    private final ModelMapper modelMapper;

    public PlantingAttemptServiceImpl(@Qualifier("attemptsRestClient") RestClient restClient, ModelMapper modelMapper) {
        this.attemptsRestClient = restClient;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addPlantingAttempt(PlantingAttemptDTO addPlantingAttemptDTO) {
        LOGGER.info("Creating Attempt!");

        attemptsRestClient
                .post()
                .uri("http://localhost:8081/attempts/add")
                .body(addPlantingAttemptDTO)
                .retrieve();
    }

    @Override
    public List<PlantingAttemptDTO> getAllPlantingAttemptsForUser(String username) {
        LOGGER.info("Get all Attempts by username");
        return attemptsRestClient
                .get()
                .uri("attempts/{username}/all", username)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    @Override
    public void deleteOffer(Long offerId) {

    }

    @Override
    public PlantingAttemptDTO getPlantingAttemptById(Long id) {
        return attemptsRestClient
                .get()
                .uri("/attempts/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(PlantingAttemptDTO.class);
    }
}
