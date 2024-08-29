package com.airtribe.news_aggregator_api_spring_boot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank
    private String firstName;
    @NotBlank(message = "name mandatory")
    private String lastName;

    @Email @NotBlank
    private String email;

    @NotBlank
    @Column(length = 60)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean isEnabled;

//    @ElementCollection
//    private List<String> preferences = new ArrayList<>();

}
