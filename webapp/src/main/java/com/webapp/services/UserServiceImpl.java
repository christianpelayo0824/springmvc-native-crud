package com.webapp.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.dao.UserDao;
import com.webapp.entity.User;

@Service
public class UserServiceImpl implements UserServices {

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public void createUser(User user) {
		userDao.create(user);
	}

	@Override
	@Transactional
	public List<User> getAllUser() {
		List<User> user = new ArrayList<>();
		user = userDao.getAllList();
		return user;
	}

	@Override
	@Transactional
	public void deleteUser(int userId) {
		userDao.delete(userId);
	}

	@Override
	@Transactional
	public User getUserById(int userId) {
		User user = (User) userDao.getById(userId);
		return user;
	}
}
