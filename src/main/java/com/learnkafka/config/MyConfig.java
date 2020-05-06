package com.learnkafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class MyConfig {

	/*
	@Profile("dev")
	@Bean
	public NewTopic getANetTopic() {
		return TopicBuilder.name("DANCE AGAIN").partitions(3).replicas(3).build();
	}
	
	@Profile("prod")
	@Bean
	public NewTopic getANetTopicProd() {
		return TopicBuilder.name("some-new-event-from-PROD").partitions(3).replicas(3).build();
	}
	*/

}
