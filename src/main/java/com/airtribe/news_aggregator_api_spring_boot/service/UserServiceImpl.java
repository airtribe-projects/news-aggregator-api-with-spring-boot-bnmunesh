package com.airtribe.news_aggregator_api_spring_boot.service;

import com.airtribe.news_aggregator_api_spring_boot.entity.Role;
import com.airtribe.news_aggregator_api_spring_boot.entity.User;
import com.airtribe.news_aggregator_api_spring_boot.entity.VerificationToken;
import com.airtribe.news_aggregator_api_spring_boot.model.LoginDto;
import com.airtribe.news_aggregator_api_spring_boot.model.UserModel;
import com.airtribe.news_aggregator_api_spring_boot.repository.UserRepository;
import com.airtribe.news_aggregator_api_spring_boot.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public User regiserUser(UserModel user) {
        User userEntity = User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(Role.USER)
                .isEnabled(false)
                .build();
        return userRepository.save(userEntity);
    }

    @Override
    public void createVerificationToken(User userEntity, String token) {
        VerificationToken verificationToken = new VerificationToken(token, userEntity);
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public boolean validateTokenAndEnableUser(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if (verificationToken == null) {
            return false;
        }
        if (verificationToken.getExpiryTime().getTime() > System.currentTimeMillis()) {
            User user = verificationToken.getUser();
            if (!user.isEnabled()) {
                user.setEnabled(true);
                userRepository.save(user);
                verificationTokenRepository.delete(verificationToken);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public User authenticateUser(LoginDto loginDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(), loginDto.getPassword()
        ));
        return userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow();
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
