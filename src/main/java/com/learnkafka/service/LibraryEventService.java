package com.learnkafka.service;

import java.util.Iterator;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnkafka.domain.LibraryEvents;
import com.learnkafka.repository.LibraryEventRepository;

@Service
public class LibraryEventService {

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	LibraryEventRepository libRepo;

	public void processAndSave(ConsumerRecord<Integer, String> consumerRecord)
			throws JsonMappingException, JsonProcessingException {
		LibraryEvents libevents = objectMapper.readValue(consumerRecord.value(), LibraryEvents.class);

		libRepo.save(libevents);
		
		this.getNeighbours().forEach(item -> item.getBook());

		Iterator<LibraryEvents> iterator = this.getNewNeighbours();

		while (iterator.hasNext()) {
			System.out.println(iterator.next().getBook());
		}
		List<LibraryEvents> array = (List<LibraryEvents>) this.getNeighbours();

		for (LibraryEvents lib : array) {
			System.out.println(lib.toString());
		}
	}

	public Iterable<LibraryEvents> getNeighbours() {
		return new Iterable<LibraryEvents>() {
			@Override
			public Iterator<LibraryEvents> iterator() {
				return null;
			}
		};
	}

	public Iterator<LibraryEvents> getNewNeighbours() {
		return new Iterator<LibraryEvents>;
	}
}
