package com.airtribe.news_aggregator_api_spring_boot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NewsApiConfig {
    @Value("${news.api.key}")
    private String newsApiKey;

    @Value("${news.api.url}")
    private String newsApiUrl;

    public String getNewsApiKey() {
        return newsApiKey;
    }

    public String getNewsApiUrl() {
        return newsApiUrl;
    }
}