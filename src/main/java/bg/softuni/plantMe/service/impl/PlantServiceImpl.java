package bg.softuni.plantMe.service.impl;

import bg.softuni.plantMe.models.DTOs.AddPlantDTO;
import bg.softuni.plantMe.models.DTOs.PlantFullInfoDTO;
import bg.softuni.plantMe.models.DTOs.PlantShortInfoDTO;
import bg.softuni.plantMe.models.Plant;
import bg.softuni.plantMe.models.PlantFamily;
import bg.softuni.plantMe.repository.PlantFamilyRepository;
import bg.softuni.plantMe.repository.PlantRepository;
import bg.softuni.plantMe.service.PlantService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlantServiceImpl implements PlantService {
    private final PlantFamilyRepository plantFamilyRepository;
    private final PlantRepository plantRepository;
    private final ModelMapper modelMapper;

    public PlantServiceImpl(PlantFamilyRepository plantFamilyRepository, PlantRepository plantRepository, ModelMapper modelMapper) {
        this.plantFamilyRepository = plantFamilyRepository;
        this.plantRepository = plantRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<String> getPlantFamilyNames() {
        return plantFamilyRepository.findAll().stream().map(PlantFamily::getName).collect(Collectors.toList());
    }

    @Override
    public void addPlant(AddPlantDTO addPlantDTO, String fileName) {
        Plant plant = modelMapper.map(addPlantDTO, Plant.class);
        Optional<PlantFamily> plantFamily = plantFamilyRepository.findByName(addPlantDTO.getPlantFamily());

        plantFamily.ifPresent(plant::setPlantFamily);
        plant.setImageUrl("/images/" + fileName);

        plantRepository.save(plant);
    }

    @Override
    public List<PlantShortInfoDTO> getAllPlantsShortInfo() {
        return plantRepository.findAll().stream()
                .map(plant -> modelMapper.map(plant, PlantShortInfoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PlantShortInfoDTO> getPlantsShortInfoByPlantFamilyName(String plantFamilyName) {
        Optional<PlantFamily> findByName = plantFamilyRepository.findByName(plantFamilyName);
        if (findByName.isPresent()) {
            return plantRepository.findPlantsByPlantFamily(findByName.get())
                    .stream()
                    .map(plant -> modelMapper.map(plant, PlantShortInfoDTO.class))
                    .collect(Collectors.toList());
        }
        throw new RuntimeException("Plant Family does not exists!");
    }

    @Override
    public PlantFullInfoDTO showPlantDetails(Long id) {
        Optional<Plant> optionalPlant = plantRepository.findById(id);

        if (optionalPlant.isPresent()) {
            PlantFullInfoDTO mapped = modelMapper.map(optionalPlant, PlantFullInfoDTO.class);
            mapped.setPlantFamily(optionalPlant.get().getPlantFamily());
            return mapped;
        }
        throw new RuntimeException("Optional plant with id " + id + " not found!");
    }

    @Override
    public List<String> getAllPlantNames() {
        return plantRepository.findAll().stream().map(Plant::getName).collect(Collectors.toList());
    }

    @Override
    public String getImageUrlByPlantName(String plantName) {
        return plantRepository.findByName(plantName)
                .orElseThrow(() -> new RuntimeException("Plant not found!"))
                .getImageUrl();
    }
}
