package bg.softuni.plantMe.models.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PlantingAttemptDTO {
    private String id;
    @NotBlank
    private String plant;
    @NotBlank
    private String username;
    @NotBlank
    @Size(max = 30)
    private String variety;
    @NotBlank
    private String plantingDate;
    @Size(max = 80)
    private String seedInfo;
    @Size(max = 80)
    private String seedlingInfo;
    @Size(min = 20 , max = 1200)
    private String mainInfo;

    public PlantingAttemptDTO() {}

    public String getMainInfo() {
        return mainInfo;
    }

    public void setMainInfo(String mainInfo) {
        this.mainInfo = mainInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
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

    @Override
    public String toString() {
        return "AddPlantingAttemptDTO{" +
                "plant='" + plant + '\'' +
                ", username=" + username +
                ", variety='" + variety + '\'' +
                ", plantingDate=" + plantingDate +
                ", seedInfo='" + seedInfo + '\'' +
                ", seedlingInfo='" + seedlingInfo + '\'' +
                '}';
    }
}
