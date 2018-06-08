package com.webapp.services;

import java.util.List;

import com.webapp.entity.User;

public interface UserServices {

	public void createUser(User user);

	public List<User> getAllUser();
	
	public User getUserById(int userId);
	
	public void deleteUser(int userId);

}
