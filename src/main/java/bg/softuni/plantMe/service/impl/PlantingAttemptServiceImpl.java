package bg.softuni.plantMe.service.impl;

import bg.softuni.plantMe.config.AttemptsApiConfig;
import bg.softuni.plantMe.models.DTOs.PlantShortInfoDTO;
import bg.softuni.plantMe.models.DTOs.PlantingAttemptDTO;
import bg.softuni.plantMe.models.DTOs.PlantingAttemptShortDTO;
import bg.softuni.plantMe.service.PlantService;
import bg.softuni.plantMe.service.PlantingAttemptService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlantingAttemptServiceImpl implements PlantingAttemptService {
    private final Logger LOGGER = LoggerFactory.getLogger(PlantingAttemptServiceImpl.class);
    private final RestClient attemptsRestClient;
    private final ModelMapper modelMapper;
    private final PlantService plantService;
    private final AttemptsApiConfig attemptsApiConfig;

    public PlantingAttemptServiceImpl(@Qualifier("attemptsRestClient") RestClient restClient, ModelMapper modelMapper, ModelMapper modelMapper1, PlantService plantService, AttemptsApiConfig attemptsApiConfig) {
        this.attemptsRestClient = restClient;
        this.modelMapper = modelMapper1;
        this.plantService = plantService;
        this.attemptsApiConfig = attemptsApiConfig;
    }

    @Override
    public void addPlantingAttempt(PlantingAttemptDTO addPlantingAttemptDTO) {
        LOGGER.info("Creating Attempt!");

        attemptsRestClient
                .post()
                .uri(attemptsApiConfig.getBaseUrl() + "/add")
                .body(addPlantingAttemptDTO)
                .retrieve();
    }

    @Override
    public List<PlantingAttemptDTO> getAllPlantingAttemptsForUser(String username) {
        LOGGER.info("Get all Attempts by username");
        return attemptsRestClient
                .get()
                .uri(attemptsApiConfig.getBaseUrl() + "/all/{username}"  , username)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    @Override
    public List<PlantingAttemptDTO> getAllPlantingAttemptsOfOtherUsers(String username) {
        LOGGER.info("Get all Attempts by username");
        return attemptsRestClient
                .get()
                .uri(attemptsApiConfig.getBaseUrl() + "/isnt/{username}", username)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    @Override
    public void deleteAttempt(Long id) {
        attemptsRestClient
                .delete()
                .uri(attemptsApiConfig.getBaseUrl() + "/{id}", id)
                .retrieve();
    }

    @Override
    public PlantingAttemptDTO getPlantingAttemptById(Long id) {
        return attemptsRestClient
                .get()
                .uri(attemptsApiConfig.getBaseUrl() + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(PlantingAttemptDTO.class);
    }

    @Override
    public List<PlantingAttemptShortDTO> getAllPlantingAttemptsShortForUser(String username) {
        return this.getAllPlantingAttemptsForUser(username)
                .stream()
                .map(this::mapToShortInfo)
                .collect(Collectors.toList());
    }

    @Override
    public List<PlantingAttemptShortDTO> getAllPlantingAttemptsShortOfOtherUsers(String username) {
        return this.getAllPlantingAttemptsOfOtherUsers(username)
                .stream()
                .map(this::mapToShortInfo)
                .collect(Collectors.toList());
    }

    @Override
    public PlantingAttemptShortDTO mapToShortInfo (PlantingAttemptDTO plantingAttemptDTO) {
        PlantingAttemptShortDTO mapped = modelMapper.map(plantingAttemptDTO, PlantingAttemptShortDTO.class);
        mapped.setImageUrl(plantService.getImageUrlByPlantName(plantingAttemptDTO.getPlant()));
        return mapped;
    }
}
