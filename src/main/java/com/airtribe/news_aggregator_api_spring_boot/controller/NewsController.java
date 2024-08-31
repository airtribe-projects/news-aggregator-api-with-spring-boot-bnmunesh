package com.airtribe.news_aggregator_api_spring_boot.controller;


import com.airtribe.news_aggregator_api_spring_boot.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

}
