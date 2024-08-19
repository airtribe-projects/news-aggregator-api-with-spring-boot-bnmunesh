package com.airtribe.news_aggregator_api_spring_boot.repository;

import com.airtribe.news_aggregator_api_spring_boot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String username);
    User findByEmail(String email);
}
