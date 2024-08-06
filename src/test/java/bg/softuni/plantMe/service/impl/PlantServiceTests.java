package bg.softuni.plantMe.service.impl;

import bg.softuni.plantMe.models.DTOs.AddPlantDTO;
import bg.softuni.plantMe.models.DTOs.PlantFullInfoDTO;
import bg.softuni.plantMe.models.DTOs.UserRegisterDTO;
import bg.softuni.plantMe.models.Plant;
import bg.softuni.plantMe.models.PlantFamily;
import bg.softuni.plantMe.models.enums.SunRequirements;
import bg.softuni.plantMe.models.enums.UserRoleEnum;
import bg.softuni.plantMe.models.user.UserEntity;
import bg.softuni.plantMe.models.user.UserRole;
import bg.softuni.plantMe.repository.PlantFamilyRepository;
import bg.softuni.plantMe.repository.PlantRepository;
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
public class PlantServiceTests {

    private PlantServiceImpl toTest;
    private Plant testPlant;
    @Mock
    PlantFamilyRepository mockPlantFamilyRepository;
    @Mock
    PlantRepository mockPlantRepository;

    @Captor
    private ArgumentCaptor<Plant> plantEntityArgumentCaptor;
    @BeforeEach
    void setUp () {
        toTest=new PlantServiceImpl(
                mockPlantFamilyRepository,
                mockPlantRepository,
                new ModelMapper()
        );

        testPlant = new Plant();
        testPlant.setId(1L);
        testPlant.setName("Test");
        testPlant.setInformation("Lorem ipsum odor amet, consectetuer adipiscing elit. Feugiat vehicula aliquam duis; neque ad dapibus velit.");
        testPlant.setImageUrl("imageUrl");
        testPlant.setMaxSpacing(15);
        testPlant.setMinSpacing(13);
        testPlant.setSunRequirements(SunRequirements.FULL_SUN);
    }

    @Test
    void testAddPlant() {
        // Arrange

        AddPlantDTO addPlantDTO = new AddPlantDTO();
        addPlantDTO.setInformation("Lorem ipsum odor amet, consectetuer adipiscing elit. Purus eget lorem enim posuere.");
        addPlantDTO.setPlantFamily("Amaryllodaceae");
        addPlantDTO.setName("Tomato");
        addPlantDTO.setMaxSpacing(15);
        addPlantDTO.setMinSpacing(12);
        addPlantDTO.setSunRequirements(SunRequirements.FULL_SUN);

        // ACT
        toTest.addPlant(addPlantDTO, "test");

        // Assert
        verify(mockPlantRepository).save(plantEntityArgumentCaptor.capture());

        Plant actualSavedPlant = plantEntityArgumentCaptor.getValue();

        Assertions.assertNotNull(actualSavedPlant);
        Assertions.assertEquals(addPlantDTO.getInformation(), actualSavedPlant.getInformation());
        Assertions.assertEquals(addPlantDTO.getPlantFamily(), "Amaryllodaceae");
        Assertions.assertEquals(addPlantDTO.getName(), actualSavedPlant.getName());
        Assertions.assertEquals(addPlantDTO.getMaxSpacing(), actualSavedPlant.getMaxSpacing());
        Assertions.assertEquals(addPlantDTO.getMinSpacing(), actualSavedPlant.getMinSpacing());
        Assertions.assertEquals(addPlantDTO.getSunRequirements(), actualSavedPlant.getSunRequirements());
        Assertions.assertEquals("/images/test", actualSavedPlant.getImageUrl());

    }

    @Test
    void testShowPlantDetails_Exists () {

        when(mockPlantRepository.findById(testPlant.getId())).thenReturn(Optional.of(testPlant));

        PlantFullInfoDTO plantFullInfoDTO = toTest.showPlantDetails(1L);

        Assertions.assertEquals(testPlant.getId(), plantFullInfoDTO.getId());
        Assertions.assertEquals(testPlant.getName(), plantFullInfoDTO.getName());
        Assertions.assertEquals(testPlant.getInformation(), plantFullInfoDTO.getInformation());
        Assertions.assertEquals(testPlant.getImageUrl(), plantFullInfoDTO.getImageUrl());
        Assertions.assertEquals(testPlant.getMaxSpacing(), plantFullInfoDTO.getMaxSpacing());
        Assertions.assertEquals(testPlant.getMinSpacing(), plantFullInfoDTO.getMinSpacing());
        Assertions.assertEquals(testPlant.getSunRequirements(), plantFullInfoDTO.getSunRequirements());

    }

    @Test
    void testShowPlantDetails_NotExist () {
        Assertions.assertThrows(ObjectNotFoundException.class,
                () -> toTest.showPlantDetails(1L));
    }

    @Test
    void testGetImageUrlByPlantName_Exists () {
        when(mockPlantRepository.findByName(testPlant.getName())).thenReturn(Optional.of(testPlant));

        String imageUrlByPlantName = toTest.getImageUrlByPlantName(testPlant.getName());

        Assertions.assertEquals(imageUrlByPlantName, testPlant.getImageUrl());
    }

    @Test
    void testGetImageUrlByPlantName_NotExist () {
        Assertions.assertThrows(ObjectNotFoundException.class,
                () -> toTest.getImageUrlByPlantName(testPlant.getName()));
    }
}
