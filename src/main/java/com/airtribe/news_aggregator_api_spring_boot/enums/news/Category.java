package com.airtribe.news_aggregator_api_spring_boot.enums.news;

public enum Category {

    ARTS_ENTERTAINMENT("Arts and Entertainment"),
    BUSINESS("Business"),
    ENVIRONMENT("Environment"),
    HEALTH("Health"),
    POLITICS("Politics"),
    SCIENCE("Science"),
    SPORTS("Sports"),
    TECHNOLOGY("Technology");

    private final String displayName;

    Category(String displayName) {
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

