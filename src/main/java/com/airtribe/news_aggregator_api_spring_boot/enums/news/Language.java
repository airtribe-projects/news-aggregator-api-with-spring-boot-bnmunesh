package com.airtribe.news_aggregator_api_spring_boot.enums.news;

public enum Language {
    ENGLISH("eng"),
    FRENCH("fra"),
    GERMAN("deu"),
    HINDI("hin"),
    KANNADA("kan");

    private final String displayName;

    Language(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
