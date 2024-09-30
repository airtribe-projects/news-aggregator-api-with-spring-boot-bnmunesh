package com.airtribe.news_aggregator_api_spring_boot.repository;

import com.airtribe.news_aggregator_api_spring_boot.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);
}
