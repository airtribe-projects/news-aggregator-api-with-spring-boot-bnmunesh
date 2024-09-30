package com.airtribe.news_aggregator_api_spring_boot.service;

import com.airtribe.news_aggregator_api_spring_boot.entity.User;
import com.airtribe.news_aggregator_api_spring_boot.entity.UserPreference;
import com.airtribe.news_aggregator_api_spring_boot.enums.news.Category;
import com.airtribe.news_aggregator_api_spring_boot.enums.news.Language;
import com.airtribe.news_aggregator_api_spring_boot.model.UserPreferenceDto;
import com.airtribe.news_aggregator_api_spring_boot.repository.UserPreferenceRepository;
import com.airtribe.news_aggregator_api_spring_boot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserPreferenceService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserPreferenceRepository userPreferenceRepository;

    public UserPreference getPreferencesForCurrentUser(Long userId) {
        return userPreferenceRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Preferences not found for user ID: " + userId));
    }


    public UserPreference updatePreferencesForCurrentUser(Long userId, UserPreferenceDto userPreferenceDto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        UserPreference userPreference = userPreferenceRepository.findByUser(user)
                .orElseGet(() -> {
                    System.out.println("UserPreference not found for user ID: " + userId+". Creating a default one.");
                    return createDefaultUserPreference(user);
                });

        // Update categories if provided
        if (userPreferenceDto.getCategories() != null) {
            updateCategories(userPreference, userPreferenceDto.getCategories());
        }

        // Update language if provided
        if (userPreferenceDto.getLang() != null) {
            userPreference.setLang(userPreferenceDto.getLang());
        }

        // Update country if provided
        if (userPreferenceDto.getCountry() != null) {
            userPreference.setCountry(userPreferenceDto.getCountry());
        }

        return userPreferenceRepository.save(userPreference);
    }

    private void updateCategories(UserPreference userPreference, Set<Category> newCategories) {
        Set<Category> currentCategories = userPreference.getCategories();
        if (currentCategories == null) {
            currentCategories = new HashSet<>();
        }

        // Add new categories
        currentCategories.addAll(newCategories);

        // Ensure we don't exceed the maximum of 10 categories
        if (currentCategories.size() > 10) {
            throw new IllegalArgumentException("Cannot have more than 10 categories");
        }

        userPreference.setCategories(currentCategories);
    }

    public UserPreference createDefaultUserPreference(User user) {
        UserPreference userPreference = new UserPreference();
        userPreference.setUser(user);
        userPreference.setCategories(new HashSet<>()); // Empty category set
        userPreference.setLang(Language.ENGLISH); // Default language set to English
        return userPreferenceRepository.save(userPreference);
    }

    public UserPreference removeCategories(Long userId, Set<Category> categoriesToRemove) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        UserPreference userPreference = user.getUserPreference();
        if (userPreference != null && userPreference.getCategories() != null) {
            userPreference.getCategories().removeAll(categoriesToRemove);
            return userPreferenceRepository.save(userPreference);
        }
        return userPreference;
    }
}
