package com.learnkafka.Integration.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.learnkafka.LibraryEventsProducerApplication;
import com.learnkafka.domain.Book;
import com.learnkafka.domain.LibraryEvents;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LibraryEventsProducerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EmbeddedKafka(topics = { "some-new-event" }, partitions = 3)
@TestPropertySource(properties = { "spring.kafka.producer.bootstrap-servers=${spring.embedded.kafka.brokers}",
		"spring.kafka.admin.properties.bootstrap.servers=${spring.embedded.kafka.brokers}" })
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

		HttpEntity<LibraryEvents> request = new HttpEntity<>(libevents, headers);

		ResponseEntity<LibraryEvents> response = testRestTemplate.exchange("/dev/libraryevents", HttpMethod.POST,
				request, LibraryEvents.class);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());

	}
}
