package bg.softuni.plantMe.models;

import bg.softuni.plantMe.models.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "plant_families")
public class PlantFamily extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String alias;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String information;
    @Column(name = "image_url")
    private String imageUrl;
    @OneToMany(mappedBy = "plantFamily")
    Set<Plant> plants;

    public PlantFamily () {
        this.plants = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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

    public Set<Plant> getPlants() {
        return plants;
    }

    public void setPlants(Set<Plant> plants) {
        this.plants = plants;
    }

}
