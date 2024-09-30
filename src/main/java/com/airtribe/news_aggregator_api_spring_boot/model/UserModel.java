package com.airtribe.news_aggregator_api_spring_boot.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserModel {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

}

