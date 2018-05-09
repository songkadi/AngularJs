package com.websystique.springboot.service;


import com.websystique.springboot.model.User;

import java.util.List;

public interface UserService {
	
	User findById(Long id);

	User findByName(String name);

	User saveUser(User user);

	User updateUser(User user);

	void deleteUserById(Long id);

	void deleteAllUsers();

	List<User> findAllUsers();

	boolean isUserExist(User user);
}