package com.airtribe.news_aggregator_api_spring_boot.controller;


import com.airtribe.news_aggregator_api_spring_boot.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    @GetMapping("/api/news")
    public Object getNews(@RequestParam(value = "preferences", defaultValue = "") String[] preferences) {
        return newsService.fetchNews(preferences);
    }
}
