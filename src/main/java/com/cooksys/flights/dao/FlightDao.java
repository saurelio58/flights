package com.cooksys.flights.dao;

import com.cooksys.flights.models.RouteModel;

public interface FlightDao {

	RouteModel getFlightsForRoute(RouteModel routeModel);



}
