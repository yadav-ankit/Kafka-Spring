package com.learnkafka.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mongo.model.User;
import com.mongo.repo.UserRepository;

@RestController
public class MongoController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		LOG.info("Getting all users.");
		return userRepository.findAll();
	}

	@RequestMapping(value = "user/{userId}", method = RequestMethod.GET)
	public Optional<User> getUser(@PathVariable String userId) {
		LOG.info("Getting user with ID: {}.", userId);
		return userRepository.findById(userId);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public User addNewUsers(@RequestBody User user) {
		LOG.info("Saving user.");
		return userRepository.save(user);
	}

	@RequestMapping(value = "/settings/{userId}", method = RequestMethod.GET)
	public Object getAllUserSettings(@PathVariable String userId) {
		Optional<User> user = userRepository.findById(userId);
		 Object xy = user.get().getUserSettings();
		return xy;
	}
}
