package com.learnkafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learnkafka.domain.LibraryEvents;
import com.learnkafka.producer.LibraryEventProducer;

@RestController
public class LibraryEventController {

	@Autowired
    private Environment env;	
	
	@Autowired
	private LibraryEventProducer libraryEventProducer;

	@PostMapping("/dev/libraryevents")
	public ResponseEntity<LibraryEvents> postLibraryEvent(@RequestBody LibraryEvents libraryevents) throws JsonProcessingException {
		//libraryevents.getBook().setBookAuthor(env.getProperty("myCustom.user"));
		libraryEventProducer.sendData(libraryevents);
		return ResponseEntity.status(HttpStatus.CREATED).body(libraryevents);
		
	}
	
	@Profile("prod")
	@PostMapping("/prod/libraryevents")
	public ResponseEntity<LibraryEvents> postLibraryEventss(@RequestBody LibraryEvents libraryevents) {
		
		libraryevents.getBook().setBookAuthor(env.getProperty("myCustom.user"));
		return ResponseEntity.status(HttpStatus.CREATED).body(libraryevents);
		
	}
	
}
