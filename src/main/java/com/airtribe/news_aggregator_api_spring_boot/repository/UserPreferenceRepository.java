package com.airtribe.news_aggregator_api_spring_boot.repository;

import com.airtribe.news_aggregator_api_spring_boot.entity.User;
import com.airtribe.news_aggregator_api_spring_boot.entity.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPreferenceRepository extends JpaRepository<UserPreference, Long> {
    Optional<UserPreference> findByUser(User user);

    @Query("SELECT up FROM UserPreference up WHERE up.user.userId = :userId")
    Optional<UserPreference> findByUserId(@Param("userId") Long userId);
}
