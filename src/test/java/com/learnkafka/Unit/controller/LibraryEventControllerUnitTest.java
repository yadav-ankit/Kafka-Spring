package com.learnkafka.Unit.controller;

import static org.hamcrest.CoreMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnkafka.controller.LibraryEventController;
import com.learnkafka.domain.Book;
import com.learnkafka.domain.LibraryEvents;
import com.learnkafka.producer.LibraryEventProducer;

@WebMvcTest(LibraryEventController.class)
@AutoConfigureWebMvc
public class LibraryEventControllerUnitTest {

	
	@Autowired
	MockMvc mockmvc;

	ObjectMapper objectMapper = new ObjectMapper();

	@MockBean
	LibraryEventProducer libraryEventProducer;

	@Test
	void posttest_Positive() throws Exception {

		Book book = new Book();

		book.setBookAuthor("Ankit");
		book.setBookId(32);
		book.setBookName("BEST Seller");

		LibraryEvents libevents = new LibraryEvents();
		libevents.setBook(book);
		libevents.setLibraryEventId(98);

		String jsonVal = objectMapper.writeValueAsString(libevents);

		doNothing().when(libraryEventProducer).sendData((LibraryEvents) isA(LibraryEvents.class));

		mockmvc.perform(post("/dev/libraryevents").content(jsonVal).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());


	}

}
