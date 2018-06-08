package com.webapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webapp.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void create(User user) {
		getSession().saveOrUpdate(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllList() {
		List<User> user = new ArrayList<>();
		user = getSession().createQuery("from User").list();
		return user;
	}

	@Override
	public User getById(int userId) {
		User user = (User) getSession().get(User.class, userId);
		return user;
	}

	@Override
	public void delete(int userId) {
		User user = (User) getSession().get(User.class, userId);
		getSession().delete(user);
	}

}
