package com.airtribe.news_aggregator_api_spring_boot.controller;

import com.airtribe.news_aggregator_api_spring_boot.entity.User;
import com.airtribe.news_aggregator_api_spring_boot.model.PreferenceDTO;
import com.airtribe.news_aggregator_api_spring_boot.service.UserService;
import com.airtribe.news_aggregator_api_spring_boot.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PutMapping("/{userId}/preferences")
    public ResponseEntity<User> updateUserPreferences(
            @PathVariable Long userId,
            @Valid @RequestBody PreferenceDTO preferencesDTO) {
        User updatedUser = userService.updateUserPreferences(userId, preferencesDTO);
        return ResponseEntity.ok(updatedUser);
    }
}
