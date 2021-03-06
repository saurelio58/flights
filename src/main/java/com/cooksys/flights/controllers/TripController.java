package com.cooksys.flights.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.flights.dao.TripDao;
import com.cooksys.flights.models.Route;
import com.cooksys.flights.models.Segment;
import com.cooksys.flights.models.Trip;

@RestController
@RequestMapping(value = "/trips")
public class TripController {
	@Autowired
	private TripDao tripDao;

	@RequestMapping(value = "/{username}", method = RequestMethod.POST)
	public Route addTrip(@PathVariable String username, @RequestBody Route route) {
		return tripDao.addTrip(username, route);
	}

	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public List<Trip> getTrips(@PathVariable String username) {
		return tripDao.getTrips(username);
	}

	@RequestMapping(value = "/cancel/{tripId}", method = RequestMethod.PUT)
	public Trip cancelTrip(@PathVariable String tripId) {
		return tripDao.cancelTrip(tripId);
	}
	
	@RequestMapping(value = "/segments/{tripId} ", method = RequestMethod.GET)
	public List<Segment> getTripDetails(@PathVariable String tripId) {
		return tripDao.getTripDetails(tripId);
	}

}
