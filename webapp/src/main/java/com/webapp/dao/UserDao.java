package com.webapp.dao;

import java.util.List;

import com.webapp.entity.User;

public interface UserDao {

	public void create(User user);

	public List<User> getAllList();

	public User getById(int userId);

	public void delete(int userId);

}
