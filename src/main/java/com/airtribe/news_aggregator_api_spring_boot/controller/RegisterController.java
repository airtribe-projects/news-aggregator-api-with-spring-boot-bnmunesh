package com.airtribe.news_aggregator_api_spring_boot.controller;

import com.airtribe.news_aggregator_api_spring_boot.entity.User;
import com.airtribe.news_aggregator_api_spring_boot.model.LoginDto;
import com.airtribe.news_aggregator_api_spring_boot.model.LoginResponse;
import com.airtribe.news_aggregator_api_spring_boot.model.UserModel;
import com.airtribe.news_aggregator_api_spring_boot.service.EmailService;
import com.airtribe.news_aggregator_api_spring_boot.service.JwtService;
import com.airtribe.news_aggregator_api_spring_boot.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class RegisterController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private JwtService jwtService;


    @GetMapping("/")
    public String home() {
        return "Hello, Welcome to news aggregator!";
    }

    @PostMapping("/api/register")
    public User registerUser(@Valid @RequestBody UserModel userDTO, HttpServletRequest request) {
        User userEntity = userService.regiserUser(userDTO);
        String verificationToken = UUID.randomUUID().toString();
        String applicationUrl = getApplicationUrl(request) + "/verifyRegistration?verificationToken=" + verificationToken;
        userService.createVerificationToken(userEntity, verificationToken);
        System.out.println("Verification Token created successfully for userDTO: " + userEntity.getEmail());

//        sending email
//        String subject = "Complete Your Registration";
//        String body = "Dear " + userEntity.getFirstName() + ",\n\n" +
//                "Thank you for registering.\nPlease click the link below to complete your registration by verifying your email. \n\n" +
//                "Link:" + applicationUrl + "\n\n" +
//                "Regards,\nMunesh";
//        emailService.sendEmail(userEntity.getEmail(), subject, body);

        System.out.println("Verification URL- " + applicationUrl + " sent to- " + userEntity.getEmail());
        return userEntity;
    }

    private String getApplicationUrl(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistrationViaGet(@RequestParam String verificationToken) {
        return verifyRegistration(verificationToken);
    }

    @PatchMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam String verificationToken) {
        boolean isValid = userService.validateTokenAndEnableUser(verificationToken);
        if (isValid)
            return "User successfully verified and enabled";
        return "Invalid Token/Token Expired";
    }

    @PostMapping("/api/login")
    private LoginResponse authenticate(@RequestBody LoginDto loginDto){
        User authenticatedUser = userService.authenticateUser(loginDto);

        //generatetoken
        String jwtToken = jwtService.generateJwtToken(authenticatedUser);

        return new LoginResponse(jwtToken, jwtService.extractExpiration(jwtToken));
    }
}
