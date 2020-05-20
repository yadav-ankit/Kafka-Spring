package com.learnkafka.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;

@Configuration
@EnableKafka
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

	public void tests() {

		ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();

		// factory.setReplyTemplate(replyTemplate);
	}



}
