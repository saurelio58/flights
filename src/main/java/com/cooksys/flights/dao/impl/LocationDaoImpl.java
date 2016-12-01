package com.cooksys.flights.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.cooksys.core.models.Location;
import com.cooksys.flights.dao.LocationDao;

@Repository
@Transactional
public class LocationDaoImpl implements LocationDao {

	@Override
	public List<Location> getLocations() {

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Location[]> responseEntity = restTemplate.getForEntity(
				"http://localhost:8080/bc-final-webservice/getLocations", Location[].class);
		Location[] locationList = responseEntity.getBody();

		ArrayList<Location> locations = new ArrayList<Location>(Arrays.asList(locationList));

		return locations;
	}

}
