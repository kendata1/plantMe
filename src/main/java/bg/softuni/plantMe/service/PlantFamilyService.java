package bg.softuni.plantMe.service;

import bg.softuni.plantMe.models.DTOs.PlantFamilyDTO;
import bg.softuni.plantMe.models.PlantFamily;

import java.util.List;

public interface PlantFamilyService {
    public void addPlantFamily (PlantFamilyDTO plantFamilyDTO, String fileName);
    public List<PlantFamilyDTO> findAllPlantFamilies ();
    public PlantFamilyDTO getPlantFamilyById (Long id);
}
