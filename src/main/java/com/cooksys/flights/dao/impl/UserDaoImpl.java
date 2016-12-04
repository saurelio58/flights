package com.cooksys.flights.dao.impl;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cooksys.flights.dao.UserDao;
import com.cooksys.flights.models.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public User addUser(User user) {
		Session session = getSession();

		// check to see if user exists
		String tempUsername = user.getUsername();
		User tempUser = getUser(tempUsername);

		if (tempUser != null) {
			// returning null indicates user already exists
			return null;
		} else {
			// create new user
			user.setLastUpdate(new Date());  // set to current date/time
			session.save(user);
			System.out.println(user.getUserId());
			return getUser(user.getUsername());
		}
	}

	@Override
	public User getUser(String username) {
		Session session = getSession();
		return (User) session.createQuery("from User u where u.username = :username").setString("username", username)
				.uniqueResult();
	}

}
