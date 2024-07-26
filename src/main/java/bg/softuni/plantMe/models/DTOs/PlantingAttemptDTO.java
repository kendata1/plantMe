package bg.softuni.plantMe.models.DTOs;

public class PlantingAttemptDTO {
    private String id;
    private String plant;
    private String username;
    private String variety;
    private String plantingDate;
    private String seedInfo;
    private String seedlingInfo;

    public PlantingAttemptDTO() {}

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
