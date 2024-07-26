package bg.softuni.plantMe.models.DTOs;

import bg.softuni.plantMe.models.Plant;
import bg.softuni.plantMe.models.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class AddPlantingAttemptDTO {
    @NotBlank
    private String plantName;
    @NotBlank
    @Size(min = 2, max = 30)
    private String variety;
    @NotBlank
    private LocalDate plantingDate;
    @Size(max = 255)
    private String seedInfo;
    @Size(max = 255)
    private String seedlingInfo;

    public AddPlantingAttemptDTO () {}

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public LocalDate getPlantingDate() {
        return plantingDate;
    }

    public void setPlantingDate(LocalDate plantingDate) {
        this.plantingDate = plantingDate;
    }

    public String getSeedInfo() {
        return seedInfo;
    }

    public void setSeedInfo(String seedInfo) {
        this.seedInfo = seedInfo;
    }

    public String getSeedlingInfo() {
        return seedlingInfo;
    }

    public void setSeedlingInfo(String seedlingInfo) {
        this.seedlingInfo = seedlingInfo;
    }
}
