package bg.softuni.plantMe.models.DTOs;

import bg.softuni.plantMe.models.PlantFamily;
import bg.softuni.plantMe.models.enums.SunRequirements;

public class PlantFullInfoDTO {
    private Long id;
    private String name;
    private PlantFamily plantFamily;
    private SunRequirements sunRequirements;
    private int minSpacing;
    private int maxSpacing;
    private String information;
    private String imageUrl;

    public PlantFullInfoDTO () {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlantFamily getPlantFamily() {
        return plantFamily;
    }

    public void setPlantFamily(PlantFamily plantFamily) {
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
