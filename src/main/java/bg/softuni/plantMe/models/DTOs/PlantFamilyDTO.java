package bg.softuni.plantMe.models.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PlantFamilyDTO {
    private Long id;
    @NotBlank(message = "{not.blank.err}")
    @Size(max = 35, message = "{short.string.max.length.err}")
    private String name;
    @NotBlank(message = "{not.blank.err}")
    @Size(max = 35, message = "{short.string.max.length.err}")
    private String alias;
    @NotBlank(message = "{not.blank.err}")
    @Size(max = 4000, message = "{information.max.length}")
    private String information;
    private String imageUrl;

    public PlantFamilyDTO() {}

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
}
