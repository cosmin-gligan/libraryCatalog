package ro.siit.catalog.model;

public enum NovelGenreEnum {
    SF("SF"),
    COMEDY("Comedy"),
    DRAMA("Drama");

    private final String name;

    NovelGenreEnum(String name) {
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
