package com.songkadi.service;


import com.songkadi.model.AppUser;

import java.util.List;

public interface AppUserService {
	
	AppUser findById(Long id);

	AppUser findByName(String name);

	AppUser saveUser(AppUser appUser);

	AppUser updateUser(AppUser appUser);

	void deleteUserById(Long id);

	void deleteAllUsers();

	List<AppUser> findAllUsers();

	boolean isUserExist(AppUser appUser);
}