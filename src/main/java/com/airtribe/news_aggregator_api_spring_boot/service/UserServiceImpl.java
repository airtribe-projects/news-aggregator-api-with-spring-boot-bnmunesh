package com.airtribe.news_aggregator_api_spring_boot.service;

import com.airtribe.news_aggregator_api_spring_boot.entity.User;
import com.airtribe.news_aggregator_api_spring_boot.model.PreferenceDTO;
import com.airtribe.news_aggregator_api_spring_boot.model.UserDto;
import com.airtribe.news_aggregator_api_spring_boot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository _userRepository;
    @Autowired
    private PasswordEncoder _passwordEncoder;
    @Autowired
    private AuthenticationManager _authenticationManager;
    @Autowired
    private JwtService _jwtService;

    @Override
    public User registerUser(UserDto user) {
        User userEntity = new User();
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(_passwordEncoder.encode(user.getPassword()));
        userEntity.setUserName(user.getUserName());
        userEntity.setRole("USER");
        return _userRepository.save(userEntity);
    }

    public User updateUserPreferences(Long userId, PreferenceDTO preferencesDTO) {
        Optional<User> userOptional = _userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPreferences(preferencesDTO.getPreferences());
            return _userRepository.save(user);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public String verifyUserCredentials(UserDto user) {
        Authentication authentication = _authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return _jwtService.generateToken(user.getEmail());
        }
        return "Invalid username or password";
    }

    @Override
    public List<User> getUsers() {
        return _userRepository.findAll();
    }
}
