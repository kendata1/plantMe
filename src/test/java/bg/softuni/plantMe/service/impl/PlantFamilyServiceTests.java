package bg.softuni.plantMe.service.impl;

import bg.softuni.plantMe.models.DTOs.PlantFamilyDTO;
import bg.softuni.plantMe.models.PlantFamily;
import bg.softuni.plantMe.repository.PlantFamilyRepository;
import bg.softuni.plantMe.service.exception.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlantFamilyServiceTests {

    private PlantFamilyServiceImpl toTest;
    @Mock
    PlantFamilyRepository mockPlantFamilyRepository;
    @Captor
    private ArgumentCaptor<PlantFamily> plantFamilyArgumentCaptor;
    @BeforeEach
    void setUp () {
        toTest=new PlantFamilyServiceImpl(
                new ModelMapper(),
                mockPlantFamilyRepository
        );
    }

    @Test
    void testAddPlantFamily () {
        PlantFamilyDTO plantFamilyDTO = new PlantFamilyDTO();
        plantFamilyDTO.setName("Name");
        plantFamilyDTO.setAlias("Alias");
        plantFamilyDTO.setInformation("Lorem ipsum odor amet, consectetuer adipiscing elit. Feugiat vehicula aliquam duis;");
        plantFamilyDTO.setId(1L);

        // ACT
        toTest.addPlantFamily(plantFamilyDTO, "test");

        // Assert
        verify(mockPlantFamilyRepository).save(plantFamilyArgumentCaptor.capture());

        PlantFamily actualSavedPlant = plantFamilyArgumentCaptor.getValue();

        Assertions.assertEquals(plantFamilyDTO.getName(), actualSavedPlant.getName());
        Assertions.assertEquals(plantFamilyDTO.getAlias(), actualSavedPlant.getAlias());
        Assertions.assertEquals("/images/test", actualSavedPlant.getImageUrl());
        Assertions.assertEquals(plantFamilyDTO.getInformation(), actualSavedPlant.getInformation());
        Assertions.assertEquals(plantFamilyDTO.getId(), actualSavedPlant.getId());
    }

    @Test
    void testGetPlantFamilyById_Exists () {

        PlantFamily testPlantFamily = new PlantFamily();
        testPlantFamily.setName("Name");
        testPlantFamily.setAlias("Alias");
        testPlantFamily.setImageUrl("ImageUrl");
        testPlantFamily.setInformation("Lorem ipsum odor amet, consectetuer adipiscing elit. Feugiat vehicula aliquam duis;");
        testPlantFamily.setId(1L);

        when(mockPlantFamilyRepository.findById(testPlantFamily.getId())).thenReturn(Optional.of(testPlantFamily));

        PlantFamilyDTO plantFamilyById = toTest.getPlantFamilyById(1L);

        Assertions.assertEquals(testPlantFamily.getName(), plantFamilyById.getName());
        Assertions.assertEquals(testPlantFamily.getAlias(), plantFamilyById.getAlias());
        Assertions.assertEquals(testPlantFamily.getImageUrl(), plantFamilyById.getImageUrl());
        Assertions.assertEquals(testPlantFamily.getInformation(), plantFamilyById.getInformation());
        Assertions.assertEquals(testPlantFamily.getId(), plantFamilyById.getId());
    }

    @Test
    void testGetPlantFamilyById_Not_Exist () {
        Assertions.assertThrows(ObjectNotFoundException.class,
        () -> toTest.getPlantFamilyById(1L));
    }

}
