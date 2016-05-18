package com.cooksys.flights.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.flights.dao.UserDao;
import com.cooksys.flights.models.User;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserDao userDao;
	@Autowired
	com.cooksys.flights.messaging.JMSFlightUpdateController JMSFlightUpdateController;
	
	// flights/api/users
	@RequestMapping(method = RequestMethod.POST)
	public User addUser(@RequestBody User user) {
		return userDao.addUser(user);
	}

	// flights/api/users/{username}
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public User getUser(@PathVariable String username) {
		return userDao.getUser(username);
	}

}
