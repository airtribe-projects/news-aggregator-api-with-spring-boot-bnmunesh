package com.airtribe.news_aggregator_api_spring_boot.controller;


import com.airtribe.news_aggregator_api_spring_boot.entity.User;
import com.airtribe.news_aggregator_api_spring_boot.model.UserPrincipal;
import com.airtribe.news_aggregator_api_spring_boot.model.apiresponse.NewsApiResponse;
import com.airtribe.news_aggregator_api_spring_boot.service.NewsApiRequestBuilder;
import com.airtribe.news_aggregator_api_spring_boot.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class NewsController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private NewsApiRequestBuilder newsApiRequestBuilder;


    @GetMapping("api/v1/newsRequestBody")
    public String getNewsRequestBody(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User user = userPrincipal.getUser();
        return newsApiRequestBuilder.buildRequestBody(user.getUserId());
    }

    @PostMapping("api/v1/news")
    public Mono<String> getNews(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User user = userPrincipal.getUser();
        String requestBody = newsApiRequestBuilder.buildRequestBody(user.getUserId());
        return newsService.getNews(requestBody);
    }

    @PostMapping("api/v2/news")
    public NewsApiResponse getNewsRest(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User user = userPrincipal.getUser();
        String requestBody = newsApiRequestBuilder.buildRequestBody(user.getUserId());
        NewsApiResponse response = newsService.getNewsRest(requestBody);
        System.out.println("responseBody: \n" + response);
        return response;
    }

    @PostMapping("api/v3/news")
    public String getNewsR(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User user = userPrincipal.getUser();
        String requestBody = newsApiRequestBuilder.buildRequestBody(user.getUserId());
        String response = newsService.getNewsR(requestBody);
        System.out.println("responseBody: \n" + response);
        return response;
    }
}
