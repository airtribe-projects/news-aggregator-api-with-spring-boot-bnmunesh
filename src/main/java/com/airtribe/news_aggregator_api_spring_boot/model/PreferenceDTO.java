package com.airtribe.news_aggregator_api_spring_boot.model;

import java.util.List;

public class PreferenceDTO {
    private List<String> preferences;

    // Getters and Setters
    public List<String> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<String> preferences) {
        this.preferences = preferences;
    }

    }
