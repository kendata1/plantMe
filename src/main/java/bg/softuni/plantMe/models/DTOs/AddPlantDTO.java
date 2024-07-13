package bg.softuni.plantMe.models.DTOs;

import bg.softuni.plantMe.models.PlantFamily;
import bg.softuni.plantMe.models.enums.SunRequirements;

public record AddPlantDTO (String name, PlantFamily plantFamily,
                           SunRequirements sunRequirements,
                           int minSpacing, int maxSpacing, String description, String imageUrl){

}
