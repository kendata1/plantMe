package bg.softuni.plantMe.models;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDate;

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
    private LocalDate plantingDate;
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

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
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
