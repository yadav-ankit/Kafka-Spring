package com.learnkafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.learnkafka.producer.LibraryEventProducer;

@Component
public class LibraryEventConsumer {

	private static final Logger log = LoggerFactory.getLogger(LibraryEventProducer.class);

	@KafkaListener(topics = { "some-new-event" })
	public void listenMessage(ConsumerRecord<Integer, String> consumerRecord) {
		
		log.info(" The Record is : {}", consumerRecord);
	}
}
