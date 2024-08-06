package bg.softuni.plantMe.models.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PlantingAttemptShortDTO {

    private String id;
    @NotBlank(message = "{not.blank.err}")
    @Size(max = 35, message = "{short.string.max.length.err}")
    private String plant;
    @NotBlank(message = "{not.blank.err}")
    private String username;
    @NotBlank(message = "{not.blank.err}")
    @Size(max = 35, message = "{short.string.max.length.err}")
    private String variety;
    @NotBlank(message = "{not.blank.err}")
    private String plantingDate;
    @NotBlank
    private String imageUrl;

    public PlantingAttemptShortDTO () {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getPlantingDate() {
        return plantingDate;
    }

    public void setPlantingDate(String plantingDate) {
        this.plantingDate = plantingDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
