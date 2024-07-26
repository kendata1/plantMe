package bg.softuni.plantMe.service;

import bg.softuni.plantMe.models.DTOs.PlantingAttemptDTO;

import java.util.List;

public interface PlantingAttemptService {
    void addPlantingAttempt (PlantingAttemptDTO addPlantingAttemptDTO);

    List<PlantingAttemptDTO> getAllPlantingAttemptsForUser (Long userId);

    void deleteOffer(Long offerId);

    PlantingAttemptDTO getPlantingAttemptById(Long id);
}
