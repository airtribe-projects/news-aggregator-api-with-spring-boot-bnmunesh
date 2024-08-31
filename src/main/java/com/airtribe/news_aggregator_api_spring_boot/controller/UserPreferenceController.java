package com.airtribe.news_aggregator_api_spring_boot.controller;

import com.airtribe.news_aggregator_api_spring_boot.entity.User;
import com.airtribe.news_aggregator_api_spring_boot.entity.UserPreference;
import com.airtribe.news_aggregator_api_spring_boot.enums.news.Category;
import com.airtribe.news_aggregator_api_spring_boot.model.UserPreferenceDto;
import com.airtribe.news_aggregator_api_spring_boot.model.UserPrincipal;
import com.airtribe.news_aggregator_api_spring_boot.service.UserPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class UserPreferenceController {

    @Autowired
    private UserPreferenceService userPreferenceService;

    @GetMapping("/api/preferences")
    public UserPreference getPreferencesForCurrentUser(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User user = userPrincipal.getUser();
        return userPreferenceService.getPreferencesForCurrentUser(user.getUserId());
    }

    @PutMapping("/api/preferences")
    public UserPreference updatePreferencesForCurrentUser(@RequestBody UserPreferenceDto userPreferenceDto, Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User user = userPrincipal.getUser();
        return userPreferenceService.updatePreferencesForCurrentUser(user.getUserId(), userPreferenceDto);
    }

    @DeleteMapping("/api/preferences/categories")
    public UserPreference removeCategories(@RequestBody Set<Category> categoriesToRemove, Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User user = userPrincipal.getUser();
        UserPreference updatedPreference = userPreferenceService.removeCategories(user.getUserId(), categoriesToRemove);
        return updatedPreference;
    }
}
