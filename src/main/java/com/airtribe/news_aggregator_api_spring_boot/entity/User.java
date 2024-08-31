package com.airtribe.news_aggregator_api_spring_boot.entity;

import com.airtribe.news_aggregator_api_spring_boot.enums.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

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

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private VerificationToken verificationToken;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private UserPreference userPreference;

}
