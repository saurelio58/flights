package com.cooksys.flights.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cooksys.core.models.Flight;
import com.cooksys.core.models.Location;
import com.cooksys.flights.controllers.UserController;
import com.cooksys.flights.dao.TripDao;
import com.cooksys.flights.models.Route;
import com.cooksys.flights.models.RouteModel;
import com.cooksys.flights.models.Segment;
import com.cooksys.flights.models.Trip;
import com.cooksys.flights.models.User;
import com.google.gson.Gson;

@Repository
@Transactional
public class TripDaoImpl implements TripDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	UserController userController;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Route addTrip(String username, Route route) {
		Session session = getSession();

		Trip userTrip = new Trip();
		User currentUser = new User();

		userTrip.setDepartDay(route.getDepartDay());
		userTrip.setArriveDay(route.getArriveDay());
		userTrip.setNumFlights(route.getNumFlights());

		// put user in Trip
		HashSet<User> users = new HashSet<User>();
		currentUser = userController.getUser(username);
		users.add(currentUser);
		userTrip.setUsers(users);
		
		// put trip in user
		Set<Trip> userTrips = new HashSet<Trip>();
		userTrips = currentUser.getTrips();
		userTrips.add(userTrip);
		currentUser.setTrips(userTrips);
		

		// put segments in Trip
		// convert from JSON to Java

		Gson gson = new Gson();

		List<Flight> flightList = new ArrayList<Flight>();
		Set<Segment> segments = new HashSet<Segment>();

		try {
			List txt = route.getFlightList();
			JSONArray jsonArray = new JSONArray(txt);
			for (int i = 0; i < jsonArray.length(); i++) {
				Flight flight = gson.fromJson(jsonArray.get(i).toString(), Flight.class);
				flightList.add(flight);
			}
		} catch (Exception e) {
			System.out.println("TripDaoImpl-unexpect error using JSON");
		}

		Location loc = new Location();

		// trip origin
		Flight flight = new Flight();
		flight = flightList.get(0);
		loc = flight.getOrigin();
		userTrip.setOrginCity(loc.getCity());
		userTrip.setOrginState(loc.getState());
		// trip destination
		flight = flightList.get(flightList.size() - 1);
		loc = flight.getDestination();
		userTrip.setDestinationState(loc.getState());
		userTrip.setDestionationCity(loc.getCity());

		for (Flight flt : flightList) {
			Segment seg = new Segment();
			seg.setFlightNumber(flt.getFlightId());
			seg.setFlightStatus('X');
			segments.add(seg);
		}
		userTrip.setSegments(segments);

		// userTrip complete at this point
		session.save(currentUser);
		session.save(userTrip);
		segments = userTrip.getSegments();
		for (Segment segment : segments) {
			Serializable rtn = session.save(segment);
//			System.out.println("TripDaoImpl-session.save(segment) return=" + rtn);
		}

		return route;

	}

	@Override
	public RouteModel getTrips(String username) {

		RouteModel routeModel = new RouteModel();
		// // get user
		// User user = new User();
		// userController.getUser(username);
		//
		// for (Trip trip : user.getTrips()) {
		// Route route = new Route();
		//
		//
		//
		// }
		//

		return routeModel;
	}

}
