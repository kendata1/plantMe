package bg.softuni.plantMe.models.DTOs;

import bg.softuni.plantMe.models.PlantFamily;
import bg.softuni.plantMe.models.enums.SunRequirements;

public class AddPlantDTO {
    private String name;
    private String plantFamily;
    private SunRequirements sunRequirements;
    private int minSpacing;
    private int maxSpacing;
    private String information;

    public AddPlantDTO () {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlantFamily() {
        return plantFamily;
    }

    public void setPlantFamily(String plantFamily) {
        this.plantFamily = plantFamily;
    }

    public SunRequirements getSunRequirements() {
        return sunRequirements;
    }

    public void setSunRequirements(SunRequirements sunRequirements) {
        this.sunRequirements = sunRequirements;
    }

    public int getMinSpacing() {
        return minSpacing;
    }

    public void setMinSpacing(int minSpacing) {
        this.minSpacing = minSpacing;
    }

    public int getMaxSpacing() {
        return maxSpacing;
    }

    public void setMaxSpacing(int maxSpacing) {
        this.maxSpacing = maxSpacing;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
