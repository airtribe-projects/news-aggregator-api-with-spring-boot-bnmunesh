package com.airtribe.news_aggregator_api_spring_boot.service;

import com.airtribe.news_aggregator_api_spring_boot.entity.User;
import com.airtribe.news_aggregator_api_spring_boot.model.PreferenceDTO;
import com.airtribe.news_aggregator_api_spring_boot.model.UserDto;

import java.util.List;

public interface UserService {
    User registerUser(UserDto user);

    User updateUserPreferences(Long userId, PreferenceDTO preferencesDTO);

    String verifyUserCredentials(UserDto user);

    List<User> getUsers();
}
