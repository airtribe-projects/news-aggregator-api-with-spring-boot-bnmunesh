package com.airtribe.news_aggregator_api_spring_boot.service;

import com.airtribe.news_aggregator_api_spring_boot.model.apiresponse.NewsApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class NewsService {

    private final String BASE_URL = "https://www.newsapi.ai/api/v1/article/getArticles";
    @Autowired
    private WebClient webClient;
    @Autowired
    private RestTemplate restTemplate;

    public Mono<String> getNews(String requestBody) {

        Mono<String> response = webClient.post()
                .uri(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(error -> {
                    System.err.println("Error fetching news: " + error.getMessage());
                    return Mono.just("Error fetching news");
                });
        return response;
    }

    public NewsApiResponse getNewsRest(String requestBody) {
        try {
            NewsApiResponse responseBody = restTemplate.postForObject(BASE_URL, requestBody, NewsApiResponse.class);
            System.out.println("json response: " + responseBody);
            return responseBody;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public String getNewsR(String requestBody) {
        try {
            String responseBody = restTemplate.postForObject(BASE_URL, requestBody, String.class);
            System.out.println("json response: " + responseBody);
            return responseBody;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

