package ro.siit.catalog.model;

public enum PaperQualityEnum {

    FINE("Fine"),
    GOOD("Good"),
    POOR("Poor");

    private final String name;

    PaperQualityEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
