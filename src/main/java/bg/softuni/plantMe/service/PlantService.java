package bg.softuni.plantMe.service;

import bg.softuni.plantMe.models.DTOs.AddPlantDTO;
import bg.softuni.plantMe.models.DTOs.PlantFullInfoDTO;
import bg.softuni.plantMe.models.DTOs.PlantShortInfoDTO;
import bg.softuni.plantMe.models.PlantFamily;

import java.util.List;

public interface PlantService {
    List<String> getPlantFamilyNames();

    void addPlant(AddPlantDTO addPlantDTO, String fileName);

    List<PlantShortInfoDTO> getAllPlantsShortInfo();

    List<PlantShortInfoDTO> getPlantsShortInfoByPlantFamilyName(String plantFamilyName);

    PlantFullInfoDTO showPlantDetails(Long id);

    List<String> getAllPlantNames();

    String getImageUrlByPlantName (String plantName);
}
