package bg.softuni.plantMe.models.DTOs;
import bg.softuni.plantMe.models.enums.SunRequirements;
import jakarta.validation.constraints.*;

public class AddPlantDTO {
    @NotBlank(message = "{not.blank.err}")
    @Size(max = 35, message = "{short.string.max.length.err}")
    private String name;
    @NotBlank(message = "{not.blank.err}")
    @Size(max = 35, message = "{short.string.max.length.err}")
    private String plantFamily;
    @NotNull(message = "{not.blank.err}")
    @Size(max = 35, message = "{short.string.max.length.err}")
    private SunRequirements sunRequirements;
    @Positive(message = "{positive.number.err}")
    @Max(value = 1000, message = "{spacing.max.value.err}")
    private int minSpacing;
    @Positive
    @Max(value = 1000, message = "{spacing.max.value.err}")
    private int maxSpacing;
    @NotBlank(message = "{not.blank.err}")
    @Size(max = 4000, message = "{information.max.length}")
    private String information;

    public AddPlantDTO () {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlantFamily() {
        return plantFamily;
    }

    public void setPlantFamily(String plantFamily) {
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
}
