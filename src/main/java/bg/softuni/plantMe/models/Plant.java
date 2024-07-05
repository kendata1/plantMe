package bg.softuni.plantMe.models;

import bg.softuni.plantMe.BaseEntity;
import bg.softuni.plantMe.models.enums.SunRequirements;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "plants")
public class Plant extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;
    @ManyToOne
    @JoinColumn(name = "plant_family_id", referencedColumnName = "id")
    private PlantFamily plantFamily;
    @Column(name = "sun_requirements", nullable = false)
    private SunRequirements sunRequirements;
    @Column(name = "min_spacing", nullable = false)
    private int minSpacing;
    @Column(name = "max_spacing", nullable = false)
    private int maxSpacing;
    @Column(nullable = false)
    private String information;
    @ManyToMany
    @JoinTable(name = "plants_companions",
    joinColumns = @JoinColumn(name = "plant_id"),
    inverseJoinColumns = @JoinColumn(name = "companion_id"))
    private Set<Plant> companionPlants;
    @OneToMany (mappedBy = "plant")
    private Set<PlantingAttempt> plantingAttempts;
    public Plant () {
        this.companionPlants = new HashSet<>();
        this.plantingAttempts = new HashSet<>();
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

    public Set<Plant> getCompanionPlants() {
        return companionPlants;
    }

    public void setCompanionPlants(Set<Plant> companionPlants) {
        this.companionPlants = companionPlants;
    }
}
