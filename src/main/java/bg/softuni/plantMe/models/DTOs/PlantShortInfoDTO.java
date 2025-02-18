package bg.softuni.plantMe.models.DTOs;

public class PlantShortInfoDTO {
    private Long id;
    private String name;
    private String imageUrl;

    public PlantShortInfoDTO () {}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
