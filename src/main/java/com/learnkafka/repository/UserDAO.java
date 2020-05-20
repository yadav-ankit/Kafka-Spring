package com.learnkafka.repository;

import java.util.List;
import com.mongo.model.User;

public interface UserDAO {

	List<User> getAllUsers();

	User getUserById(String userId);

	User addNewUser(User user);

	Object getAllUserSettings(String userId);

	String getUserSetting(String userId, String key);

	String addUserSetting(String userId, String key, String value);
}
