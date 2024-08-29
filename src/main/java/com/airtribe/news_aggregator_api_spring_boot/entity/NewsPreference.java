//package com.airtribe.news_aggregator_api_spring_boot.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.List;
//
//@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class NewsPreference {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(nullable = false, name = "userId")
//    private User user;
//
////    private List<String> categories;
//
//}
