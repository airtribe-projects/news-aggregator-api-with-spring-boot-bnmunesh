package com.airtribe.news_aggregator_api_spring_boot.controller;

import com.airtribe.news_aggregator_api_spring_boot.entity.User;
import com.airtribe.news_aggregator_api_spring_boot.model.PreferenceDTO;
import com.airtribe.news_aggregator_api_spring_boot.model.UserDto;
import com.airtribe.news_aggregator_api_spring_boot.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/api/register")
    public User register(@RequestBody UserDto user) {
        return userService.registerUser(user);
    }

    @GetMapping("/api/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/api/login")
    public String login(@RequestBody UserDto user) {
        return userService.verifyUserCredentials(user);
    }

    @PutMapping("/api/{userId}/preferences")
    public ResponseEntity<User> updateUserPreferences(
            @PathVariable Long userId,
            @Valid @RequestBody PreferenceDTO preferencesDTO) {
        User updatedUser = userService.updateUserPreferences(userId, preferencesDTO);
        return ResponseEntity.ok(updatedUser);
    }
}
