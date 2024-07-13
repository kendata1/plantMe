package bg.softuni.plantMe.models.enums;

public enum SunRequirements {
    FULL_SUN("Full sun"), PARTIAL_SUN("Partial sun"), FULL_SHADE("Full shade");
    public final String label;
    private SunRequirements(String label) {
        this.label=label;
    }
}
