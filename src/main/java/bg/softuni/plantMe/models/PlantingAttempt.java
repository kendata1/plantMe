package bg.softuni.plantMe.models;

import bg.softuni.plantMe.BaseEntity;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "planting_attempts")
public class PlantingAttempt extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "plant_id", referencedColumnName = "id")
    private Plant plant;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userEntity;
    @Column(nullable = false)
    private String variety;
    @Column(name = "planting_date", nullable = false)
    private Instant plantingDate;
    private String seedInfo;
    private String seedlingInfo;

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public UserEntity getUser() {
        return userEntity;
    }

    public void setUser(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public Instant getPlantingDate() {
        return plantingDate;
    }

    public void setPlantingDate(Instant plantingDate) {
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
