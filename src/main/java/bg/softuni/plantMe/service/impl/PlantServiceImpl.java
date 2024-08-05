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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlantServiceImpl implements PlantService {
    private final PlantFamilyRepository plantFamilyRepository;
    private final PlantRepository plantRepository;
    private final ModelMapper modelMapper;
    private static final String IMAGES_DIRECTORY = System.getProperty("user.dir") + "\\plantMe\\src\\main\\resources\\static\\";

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

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deletePlant(Long id) {
        Plant plant = plantRepository.findById(id).orElseThrow(() -> new RuntimeException("Plant not found!"));
        File file = new File(IMAGES_DIRECTORY + plant.getImageUrl());

        try {
            if (file.delete()) {
                System.out.println(file.getName() + " deleted.");
            } else {
                System.out.println("Delete file failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        plantRepository.deleteById(id);
    }
}
