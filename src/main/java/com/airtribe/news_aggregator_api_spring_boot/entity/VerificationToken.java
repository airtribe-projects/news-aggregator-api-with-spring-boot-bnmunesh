package com.airtribe.news_aggregator_api_spring_boot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long tokenId;

    private String token;

    private Date expiryTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name="userId")
    @JsonIgnore
    private User user;

    public VerificationToken(String token, User user){
        this.token = token;
        this.user = user;
        this.expiryTime = calculateExpirationTime();
    }

    private Date calculateExpirationTime() {
        long expiryTimeInMinutes = 20;
        long expirationTimeInMilliseconds = expiryTimeInMinutes * 60 *1000;
        return new Date(System.currentTimeMillis() + expirationTimeInMilliseconds);
    }
}
