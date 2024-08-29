package com.airtribe.news_aggregator_api_spring_boot.service;

import com.airtribe.news_aggregator_api_spring_boot.entity.User;
import com.airtribe.news_aggregator_api_spring_boot.model.LoginDto;
import com.airtribe.news_aggregator_api_spring_boot.model.UserModel;

import java.util.List;

public interface UserService {
    User regiserUser(UserModel user);

    void createVerificationToken(User userEntity, String token);

    boolean validateTokenAndEnableUser(String token);

    User authenticateUser(LoginDto loginDto);

    List<User> getUsers();
}
