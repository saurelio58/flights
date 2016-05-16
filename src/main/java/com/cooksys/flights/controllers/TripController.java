package com.cooksys.flights.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.flights.dao.TripDao;
import com.cooksys.flights.dao.UserDao;
import com.cooksys.flights.models.RouteModel;
import com.cooksys.flights.models.User;

@RestController
@RequestMapping(value = "/trips")
public class TripController {
	@Autowired
	private TripDao tripDao;

	// flights/api/trips/{username}
	@RequestMapping(value = "/{username}", method = RequestMethod.POST)
	public User addTrip(@RequestBody RouteModel route) {
		return tripDao.addTrip(route);
	}

	// flights/api/users/{username}
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public User getTrip(@PathVariable String username) {
		return tripDao.getTrip(username);
	}

}
