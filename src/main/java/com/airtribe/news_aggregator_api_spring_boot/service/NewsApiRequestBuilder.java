package com.airtribe.news_aggregator_api_spring_boot.service;

import com.airtribe.news_aggregator_api_spring_boot.entity.User;
import com.airtribe.news_aggregator_api_spring_boot.entity.UserPreference;
import com.airtribe.news_aggregator_api_spring_boot.enums.news.Category;
import com.airtribe.news_aggregator_api_spring_boot.enums.news.Country;
import com.airtribe.news_aggregator_api_spring_boot.enums.news.Language;
import com.airtribe.news_aggregator_api_spring_boot.repository.UserPreferenceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class NewsApiRequestBuilder {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private UserPreferenceRepository userPreferenceRepository;

    @Value("${news.api.key}")
    private String apiKey;

    public String buildRequestBody(Long userId){

        UserPreference userPreference = userPreferenceRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Preferences not found for user ID: " + userId));

        ObjectNode requestBody = objectMapper.createObjectNode();
            ObjectNode query = requestBody.putObject("query");
                ObjectNode innerQuery = query.putObject("$query");
                    ArrayNode andArray = innerQuery.putArray("$and");
                        //Add user preferences inside andArray
                ObjectNode innerFilter = query.putObject("$filter");
                    innerFilter.put("isDuplicate","skipDuplicates");
            requestBody.put("resultType", "articles");
            requestBody.put("articlesSortBy", "date");
            requestBody.put("includeArticleCategories", true);
            requestBody.put("includeArticleImage", true);
            requestBody.put("apiKey", apiKey);

            // Add categories
            if (userPreference.getCategories() != null){
                addCategories(andArray, userPreference.getCategories());
            }
            // Add Country
            if(userPreference.getCountry() != null){
                addCountry(andArray, userPreference.getCountry());
            }
            // Add date range & Lang
            addDateRangeAndLang(andArray, userPreference.getLang());

        return requestBody.toString();
    }

    private void addCategories(ArrayNode andArray, Set<Category> categories) {
        ObjectNode categoryNode = andArray.addObject();
        ArrayNode orArray = categoryNode.putArray("$or");
        for (Category category : categories) {
            orArray.addObject().put("categoryUri","news/"+category);
        }
    }

    private void addCountry(ArrayNode andArray, Country country) {
        ObjectNode countryNode = andArray.addObject();
        countryNode.put("locationUri", "http://en.wikipedia.org/wiki/"+country);

    }

    private void addDateRangeAndLang(ArrayNode andArray, Language language) {
        ObjectNode dateRangeNode = andArray.addObject();
        dateRangeNode.put("dateStart","2024-08-26");
        dateRangeNode.put("dateEnd","2024-09-02");
        dateRangeNode.put("lang",language.toString());
    }

}
