package com.airtribe.news_aggregator_api_spring_boot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @NotBlank(message = "name is mandatory")
    private String userName;

    @Email
    private String email;

    @Column(length = 60)
    private String password;

    private String role;
//    private boolean isEnabled;

    @ElementCollection
    private List<String> preferences = new ArrayList<>();
}
