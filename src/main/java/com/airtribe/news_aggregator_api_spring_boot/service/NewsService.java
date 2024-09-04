package com.airtribe.news_aggregator_api_spring_boot.service;

import com.airtribe.news_aggregator_api_spring_boot.model.apiresponse.NewsApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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
    @Autowired
    private ObjectMapper objectMapper;

    public NewsApiResponse getNewsRest(String requestBody) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(requestBody, httpHeaders);
            ResponseEntity<String> responseBody = restTemplate.exchange(
                    BASE_URL,
                    HttpMethod.POST,
                    entity,
                    String.class
            );
            NewsApiResponse response = objectMapper.readValue(responseBody.getBody(), NewsApiResponse.class);
            return response;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Mono<String> getNews(String requestBody) {
            Mono<String> response = webClient.post()
                    .uri(BASE_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .doOnNext(apiResponse -> System.out.println("Raw Response Length: " +apiResponse.length()))
                    .onErrorResume(error -> {
                        System.err.println("Error fetching news: " + error.getMessage());
                        return Mono.just("Error fetching news");
                    });
            return response;
    }

    public Mono<NewsApiResponse> getNews1(String requestBody) {
            Mono<NewsApiResponse> response = webClient.post()
                    .uri(BASE_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(NewsApiResponse.class)
                    .doOnNext(apiResponse -> System.out.println("Raw Response Length: " +apiResponse.getArticles().getTotalResults()))
                    .onErrorResume(error -> {
                        System.err.println("Error fetching news: " + error.getMessage());
                        return Mono.error(error);
                    });
            return response;
    }
}

