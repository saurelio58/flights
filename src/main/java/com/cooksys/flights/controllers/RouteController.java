package com.cooksys.flights.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.core.models.Flight;
import com.cooksys.flights.dao.RouteDao;
import com.cooksys.flights.models.Route;
import com.cooksys.flights.models.RouteModel;
import com.cooksys.flights.models.User;

@RestController
@RequestMapping(value = "/routes")
public class RouteController {
	@Autowired
	private RouteDao routeDao;

	// flights/api/routes
	@RequestMapping(method = RequestMethod.POST)
	public RouteModel getFlightsForRoute(@RequestBody RouteModel routeModel) {
		return routeDao.getFlightsForRoute(routeModel);
	}

}
