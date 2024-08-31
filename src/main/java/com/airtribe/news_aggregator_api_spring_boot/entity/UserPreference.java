package com.airtribe.news_aggregator_api_spring_boot.entity;

import com.airtribe.news_aggregator_api_spring_boot.enums.news.Category;
import com.airtribe.news_aggregator_api_spring_boot.enums.news.Country;
import com.airtribe.news_aggregator_api_spring_boot.enums.news.Language;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_preferences")
public class UserPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long preferenceId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    @JsonIgnore
    private User user;

    @ElementCollection
    @CollectionTable(
            name = "preference_categories",
            joinColumns = @JoinColumn(name = "preference_id"))
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Set<Category> categories;

    @Enumerated(EnumType.STRING)
    private Language lang;

    @Enumerated(EnumType.STRING)
    private Country country;

}
