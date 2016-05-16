package com.cooksys.flights.dao;

import com.cooksys.flights.models.RouteModel;
import com.cooksys.flights.models.User;

public interface TripDao {

	User addTrip(RouteModel route);

	User getTrip(String username);

}
