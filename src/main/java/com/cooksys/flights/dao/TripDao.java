package com.cooksys.flights.dao;

import com.cooksys.core.models.Flight;
import com.cooksys.flights.models.Route;
import com.cooksys.flights.models.RouteModel;
import com.cooksys.flights.models.User;

public interface TripDao {

	Route addTrip(String username, Route route);

	RouteModel getTrips(String username);

	

}
