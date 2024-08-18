package com.airtribe.news_aggregator_api_spring_boot.service;

import com.airtribe.news_aggregator_api_spring_boot.config.ApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NewsService {

    @Autowired
    private ApiConfig apiConfig;

    public Object fetchNews(String[] preferences) {
        RestTemplate restTemplate = new RestTemplate();
        String query = String.join(" OR ", preferences);
        String url = String.format("%s?q=%s&apiKey=%s", apiConfig.getApiUrl(), query, apiConfig.getApiKey());

        return restTemplate.getForObject(url, Object.class);
    }


}

