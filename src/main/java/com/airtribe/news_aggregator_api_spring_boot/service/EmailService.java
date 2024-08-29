package com.airtribe.news_aggregator_api_spring_boot.service;

public interface EmailService {
    void sendEmail(String toEmail, String subject, String body);
}
