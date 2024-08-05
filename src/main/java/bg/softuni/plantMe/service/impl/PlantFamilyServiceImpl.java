package bg.softuni.plantMe.service.impl;

import bg.softuni.plantMe.models.DTOs.PlantFamilyDTO;
import bg.softuni.plantMe.models.Plant;
import bg.softuni.plantMe.models.PlantFamily;
import bg.softuni.plantMe.repository.PlantFamilyRepository;
import bg.softuni.plantMe.service.PlantFamilyService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlantFamilyServiceImpl implements PlantFamilyService {
    private static final String IMAGES_DIRECTORY = System.getProperty("user.dir") + "\\plantMe\\src\\main\\resources\\static\\";
    private final ModelMapper modelMapper;
    private final PlantFamilyRepository plantFamilyRepository;

    public PlantFamilyServiceImpl(ModelMapper modelMapper, PlantFamilyRepository plantFamilyRepository) {
        this.modelMapper = modelMapper;
        this.plantFamilyRepository = plantFamilyRepository;
    }

    @Override
    public void addPlantFamily(PlantFamilyDTO plantFamilyDTO, String fileName) {
        PlantFamily plantFamily = modelMapper.map(plantFamilyDTO, PlantFamily.class);
        plantFamily.setImageUrl("/images/" + fileName);
        plantFamilyRepository.save(plantFamily);
    }

    @Override
    public List<PlantFamilyDTO> findAllPlantFamilies() {
        return plantFamilyRepository.findAll().stream().map(plantFamily -> modelMapper
                .map(plantFamily,PlantFamilyDTO.class)).collect(Collectors.toList());
    }

    @Override
    public PlantFamilyDTO getPlantFamilyById(Long id) {
        return plantFamilyRepository.findById(id)
                .map(plantFamily -> modelMapper.map(plantFamily,PlantFamilyDTO.class))
                .orElseThrow();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteFamily(Long id) {
        PlantFamily plantFamily = plantFamilyRepository.findById(id).orElseThrow(() -> new RuntimeException("Plant not found!"));
        File file = new File(IMAGES_DIRECTORY + plantFamily.getImageUrl());

        try {
            if (file.delete()) {
                System.out.println(file.getName() + " deleted.");
            } else {
                System.out.println("Delete file failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        plantFamilyRepository.deleteById(id);
    }
}
