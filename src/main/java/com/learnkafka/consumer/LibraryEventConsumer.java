package com.learnkafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.learnkafka.producer.LibraryEventProducer;
import com.learnkafka.service.LibraryEventService;

@Component
public class LibraryEventConsumer {

	private static final Logger log = LoggerFactory.getLogger(LibraryEventProducer.class);

	@Autowired
	LibraryEventService libraryEventService;

	@KafkaListener(topics = { "some-new-event" })
	public void listenMessage(ConsumerRecord<Integer, String> consumerRecord)
			throws JsonMappingException, JsonProcessingException {
		
		// libraryEventService.processAndSave(consumerRecord);

		log.info(" The Record is : {}", consumerRecord);
	}
}
