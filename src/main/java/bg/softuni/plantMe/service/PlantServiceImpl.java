package bg.softuni.plantMe.service;

import bg.softuni.plantMe.models.PlantFamily;
import bg.softuni.plantMe.repository.PlantFamilyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlantServiceImpl implements PlantService{
    private final PlantFamilyRepository plantFamilyRepository;

    public PlantServiceImpl(PlantFamilyRepository plantFamilyRepository) {
        this.plantFamilyRepository = plantFamilyRepository;
    }

    @Override
    public List<String> getPlantFamilyNames() {
        return plantFamilyRepository.findAll().stream().map(PlantFamily::getName).collect(Collectors.toList());
    }
}
