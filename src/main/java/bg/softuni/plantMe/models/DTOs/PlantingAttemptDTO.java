package bg.softuni.plantMe.models.DTOs;

public class PlantingAttemptDTO {
    private Long id;
    private String plant;
    private Long userId;
    private String variety;
    private String plantingDate;
    private String seedInfo;
    private String seedlingInfo;

    public PlantingAttemptDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
                ", userId=" + userId +
                ", variety='" + variety + '\'' +
                ", plantingDate=" + plantingDate +
                ", seedInfo='" + seedInfo + '\'' +
                ", seedlingInfo='" + seedlingInfo + '\'' +
                '}';
    }
}
