package com.airtribe.news_aggregator_api_spring_boot.model;

import com.airtribe.news_aggregator_api_spring_boot.enums.news.Category;
import com.airtribe.news_aggregator_api_spring_boot.enums.news.Country;
import com.airtribe.news_aggregator_api_spring_boot.enums.news.Language;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPreferenceDto {
    private Set<Category> categories;
    private Language lang;
    private Country country;
}
