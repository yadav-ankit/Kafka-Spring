package com.learnkafka.Integration.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.learnkafka.domain.Book;
import com.learnkafka.domain.LibraryEvents;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LibraryEventControllerIntegrationTest {

	@Autowired
	TestRestTemplate testRestTemplate;
	
	@Test
	void postLibEvent() {
		
		Book book = new Book();
		
		book.setBookAuthor("Ankit");
		book.setBookId(32);
		book.setBookName("BEST Seller");

		
		LibraryEvents libevents = new LibraryEvents();
		libevents.setBook(book);
		libevents.setLibraryEventId(98);	
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.set("content-type", MediaType.APPLICATION_JSON.toString());
		
		HttpEntity<LibraryEvents> request = new HttpEntity<>(libevents,headers);
		
		ResponseEntity<LibraryEvents> response =   testRestTemplate.exchange("/dev/libraryevents", HttpMethod.POST,request,LibraryEvents.class);
		
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		
		
	}
}
