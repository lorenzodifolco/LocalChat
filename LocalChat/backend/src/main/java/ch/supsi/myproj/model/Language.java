package ch.supsi.myproj.model;

public enum Language {
    ITALIANO("it"),
    ENGLISH("en");

    private final String en;

    public String getEn() {
        return en;
    }

    Language(String en) {
        this.en = en;
    }
}
