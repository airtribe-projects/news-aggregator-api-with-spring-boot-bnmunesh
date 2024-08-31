package com.airtribe.news_aggregator_api_spring_boot.service;

import com.airtribe.news_aggregator_api_spring_boot.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;


public class NewsApiRequestBuilder {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Value("${news.api.key}")
    private String apiKey;

    public String buildRequestBody(User user){
        ObjectNode requestBody = objectMapper.createObjectNode();
        ObjectNode query = requestBody.putObject("query");
        ObjectNode innerQuery = query.putObject("$query");
        return null;
    }

}
