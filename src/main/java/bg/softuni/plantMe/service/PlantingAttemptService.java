package bg.softuni.plantMe.service;

import bg.softuni.plantMe.models.DTOs.PlantingAttemptDTO;
import bg.softuni.plantMe.models.DTOs.PlantingAttemptShortDTO;

import java.util.List;

public interface PlantingAttemptService {
    void addPlantingAttempt (PlantingAttemptDTO addPlantingAttemptDTO);

    List<PlantingAttemptDTO> getAllPlantingAttemptsForUser (String username);
    List<PlantingAttemptShortDTO> getAllPlantingAttemptsShortForUser(String username);
    List<PlantingAttemptDTO> getAllPlantingAttemptsOfOtherUsers(String username);
    List<PlantingAttemptShortDTO> getAllPlantingAttemptsShortOfOtherUsers(String username);
    void deleteAttempt(Long id);
    PlantingAttemptDTO getPlantingAttemptById(Long id);
    PlantingAttemptShortDTO mapToShortInfo (PlantingAttemptDTO plantingAttemptDTO);
}
