package com.learnkafka.repository;

import org.springframework.data.repository.CrudRepository;

import com.learnkafka.domain.LibraryEvents;

public interface LibraryEventRepository extends CrudRepository<LibraryEvents, Integer> {

}
