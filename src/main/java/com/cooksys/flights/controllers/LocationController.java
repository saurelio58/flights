package com.cooksys.flights.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.core.models.Location;
import com.cooksys.flights.dao.LocationDao;

@RestController
@RequestMapping(value = "/locations")
public class LocationController {

	@Autowired
	private LocationDao locationDao;

	// flights/api/locations
	@RequestMapping(method = RequestMethod.GET)
	public List<Location> getLocations() {
		return locationDao.getLocations();
	}

}
