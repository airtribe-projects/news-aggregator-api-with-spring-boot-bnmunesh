package com.airtribe.news_aggregator_api_spring_boot.model.apiresponse;

public class NewsApiResponse {
    private Articles articles;

    public Articles getArticles() {
        return articles;
    }

    public void setArticles(Articles articles) {
        this.articles = articles;
    }
}
