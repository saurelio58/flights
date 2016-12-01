package com.cooksys.flights.dao;

import com.cooksys.flights.models.User;

public interface UserDao {

	User addUser(User user);

	User getUser(String username);

}
