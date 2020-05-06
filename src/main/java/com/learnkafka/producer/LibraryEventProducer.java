package com.learnkafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnkafka.domain.LibraryEvents;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LibraryEventProducer {

	
	@Autowired
	KafkaTemplate<Integer, String> kafkaTemplate;
	
	@Autowired
	ObjectMapper objectMapper;
	
	private static final Logger log = LoggerFactory.getLogger(LibraryEventProducer.class);
	
	public void sendData(LibraryEvents libraryEvents) throws JsonProcessingException {
		
		Integer key = libraryEvents.getLibraryEventId();
		String value = objectMapper.writeValueAsString(libraryEvents);
		
		ListenableFuture<SendResult<Integer, String >> listenableFuture =  kafkaTemplate.sendDefault(key, value);
		
		listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {

			@Override
			public void onSuccess(SendResult<Integer, String> result) {
				log.info("The result is succcesfull {}" , result);
			}

			@Override
			public void onFailure(Throwable ex) {	
				try {
					throw ex;
				}catch (Throwable e) {
					log.error("We got error here {} with Key {} and Value { } ",ex , key , value);
					e.printStackTrace();
				}
				
			}
		});
	}
}

