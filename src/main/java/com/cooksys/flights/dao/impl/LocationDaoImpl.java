package com.cooksys.flights.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.cooksys.core.models.FlightModel;
import com.cooksys.core.models.Location;
import com.cooksys.flights.controllers.LocationDao;
import com.cooksys.flights.models.Route;

@Repository
@Transactional
public class LocationDaoImpl implements LocationDao {
	// @Autowired
	// private SessionFactory sessionFactory;

	// private Session getSession() {
	// return sessionFactory.getCurrentSession();
	// }

	@Override
	public List<Location> getLocations() {

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Location[]> responseEntity = restTemplate.getForEntity(
				"http://localhost:8080/bc-final-webservice/getLocations", Location[].class);
		Location[] locs = responseEntity.getBody();

		ArrayList<Location> locations = new ArrayList<Location>(Arrays.asList(locs));
		
		return locations;
	}

}
