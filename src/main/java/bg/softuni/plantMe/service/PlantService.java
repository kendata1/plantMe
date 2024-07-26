package bg.softuni.plantMe.service;

import bg.softuni.plantMe.models.DTOs.AddPlantDTO;
import bg.softuni.plantMe.models.DTOs.PlantFullInfoDTO;
import bg.softuni.plantMe.models.DTOs.PlantShortInfoDTO;

import java.util.List;

public interface PlantService {
    public List<String> getPlantFamilyNames ();
    public void addPlant (AddPlantDTO addPlantDTO, String fileName);
    public List<PlantShortInfoDTO> getAllPlantsShortInfo();
    public PlantFullInfoDTO showPlantDetails(Long id);
    public List<String> getAllPlantNames();
}
