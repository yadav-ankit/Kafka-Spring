package com.learnkafka.Integration.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.TestPropertySource;

import com.learnkafka.LibraryEventsProducerApplication;
import com.learnkafka.domain.Book;
import com.learnkafka.domain.LibraryEvents;

@SpringBootTest(classes = LibraryEventsProducerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EmbeddedKafka(topics = { "some-new-event" }, partitions = 3)
@TestPropertySource(properties = { "spring.kafka.producer.bootstrap-servers=${spring.embedded.kafka.brokers}",
		"spring.kafka.admin.properties.bootstrap.servers=${spring.embedded.kafka.brokers}" })
public class LibraryEventControllerIntegrationTest {

	@Autowired
	TestRestTemplate testRestTemplate;

	@Autowired
	EmbeddedKafkaBroker embeddedKafkaBroker;

	private Consumer<Integer, String> consumer;



	@BeforeEach
	void setup() {
		Map<String, Object> configs = new HashMap<>(KafkaTestUtils.consumerProps("group1", "true", embeddedKafkaBroker));
		consumer = new DefaultKafkaConsumerFactory<>(configs, new IntegerDeserializer(),
				new org.apache.kafka.common.serialization.StringDeserializer()).createConsumer();
		
		embeddedKafkaBroker.consumeFromAllEmbeddedTopics(consumer);
		
	}

	@AfterEach
	void tearDown() {
		consumer.close();
	}

	@Test
	void postLibEvent() throws InterruptedException {

		CountDownLatch latch = new CountDownLatch(1);

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

		System.out.println("BEFORE GETTING");

		ConsumerRecord<Integer, String> consumerRecord = KafkaTestUtils.getSingleRecord(consumer, "some-new-event");
		latch.countDown();
		latch.await();
		Thread.interrupted();
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAA");
		System.out.println("After comsume record");
		String value = consumerRecord.value();
		String expected = "{\"libraryEventId\":98,\"book\":{\"bookId\":32,\"bookName\":\"BEST Seller\",\"bookAuthor\":\"Ankit\"}}";
		System.out.println("AND THE VALUE OBTAINED IS " + value);

		assertEquals(expected, value);

	}
}
