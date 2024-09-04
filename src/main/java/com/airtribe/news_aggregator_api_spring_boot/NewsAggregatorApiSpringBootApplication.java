package com.airtribe.news_aggregator_api_spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableScheduling
@SpringBootApplication
public class NewsAggregatorApiSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsAggregatorApiSpringBootApplication.class, args);
	}

}
