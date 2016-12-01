package com.cooksys.flights.dao;

import java.util.List;

import com.cooksys.flights.models.Route;
import com.cooksys.flights.models.Segment;
import com.cooksys.flights.models.Trip;

public interface TripDao {

	Route addTrip(String username, Route route);

	List<Trip> getTrips(String username);

	Trip cancelTrip(String tripId);

	List<Segment>  getTripDetails(String tripId);

	

	

}
