package com.cooksys.flights.dao.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.cooksys.core.models.Flight;
import com.cooksys.core.models.FlightModel;
import com.cooksys.core.models.Location;
import com.cooksys.flights.dao.RouteDao;
import com.cooksys.flights.models.Route;
import com.cooksys.flights.models.RouteModel;

@Repository
@Transactional
public class RouteDaoImpl implements RouteDao {
	@Autowired
	private SessionFactory sessionFactory;

	// private FlightModel flightModel;
	// private RouteModel routeModel;
	private Location userOrigin;
	// private Location userDestination;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public RouteModel getFlightsForRoute(RouteModel routeModel) {

		// TEST DATA
		final String TENNESSEE = "Tennessee";
		final String NASHVILLE = "Nashville";
		final String MEMPHIS = "Memphis";
		final String KNOXVILLE = "Knoxville";
		final String GEORGIA = "Georgia";
		final String ATLANTA = "Atlanta";

		Location Nashville = new Location(TENNESSEE, NASHVILLE);
		Location Knoxville = new Location(TENNESSEE, KNOXVILLE);
		Location Memphis = new Location(TENNESSEE, MEMPHIS);
		Location Atlanta = new Location(GEORGIA, ATLANTA);

		Flight knoNas = new Flight();
		Flight memNas = new Flight();
		Flight nasAtl = new Flight();

		knoNas.setFlightId(1);
		knoNas.setOrigin(Knoxville);
		knoNas.setDestination(Nashville);
		knoNas.setDeparture(1);
		knoNas.setEta(1);

		memNas.setFlightId(2);
		memNas.setOrigin(Memphis);
		memNas.setDestination(Nashville);
		memNas.setDeparture(1);
		memNas.setEta(1);

		nasAtl.setFlightId(3);
		nasAtl.setOrigin(Nashville);
		nasAtl.setDestination(Atlanta);
		nasAtl.setDeparture(3);
		nasAtl.setEta(1);

		List<Flight> testFlightList = new ArrayList<Flight>();
		testFlightList.add(knoNas);
		testFlightList.add(memNas);
		testFlightList.add(nasAtl);

		FlightModel testFM = new FlightModel();
		testFM.setCurrentDay(1);
		testFM.setSecondsTillNextDay(1000L);
		testFM.setFlights(testFlightList);
		// TEST DATA

		FlightModel flightModel;
		// Location userOrigin;
		Location userDestination;

		// provide a routes list for routeModel - it will be null at this point
		List<Route> routeList = new ArrayList<Route>();
		routeModel.setRoutes(routeList);

		userOrigin = routeModel.getOrigin();
		userDestination = routeModel.getDestination();

		flightModel = testFM;

		// TESTING CODE for FlightModel
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// // get FlightModel from service
		// RestTemplate restTemplate = new RestTemplate();
		// flightModel = restTemplate.getForObject(
		// "http://localhost:8080/bc-final-webservice/getFlightModel",
		// FlightModel.class);

		// copy all "valid" flights to a new array
		List<Flight> flightsAvailableList = new ArrayList<Flight>();
		for (Flight flight : flightModel.getFlights()) {
			if (flight.getDeparture() >= 0)
				flightsAvailableList.add(flight);
		}

		System.out.println("RouteDaoImpl.getFlightsForRoute-flightsAvailableList.size="
				+ flightsAvailableList.size());

		// loop all flights to find those going to user's destination
		for (Flight flight : flightsAvailableList) {
			if (flight.getDestination().equals(userDestination)) {
				// we have a possible route
				Route route = new Route();
				LinkedList<Flight> flightList = new LinkedList<Flight>();
				route.setFlightList(flightList);
				flightList.add(flight);

				if (flight.getOrigin().equals(userOrigin)) {
					route.setRouteComplete(true);
					// add the complete route to the routeList
					routeList = routeModel.getRoutes();
					routeList.add(route);
					routeModel.setRoutes(routeList);
				} else {

					route = completeRoute(route, flightsAvailableList);

					if (route != null) {
						// get flight at beginning of the route
						flightList = new LinkedList<Flight>();
						flightList = route.getFlightList();
						Flight beginningFlight = flightList.get(0);
						// check to see if route starts at userOrigin
						if (beginningFlight.getOrigin().equals(userOrigin)) {
							route.setRouteComplete(true);
							// add the complete route to the routeList
							routeList = routeModel.getRoutes();
							routeList.add(route);
							routeModel.setRoutes(routeList);
						}
					}
				}

			}
		}

		return routeModel;
	}

	private Route completeRoute(Route routeParm, List<Flight> flightsAvailableParm) {

		Route route = new Route();
		List<Flight> flightsAvailable = new ArrayList<Flight>();
		route.setFlightList(routeParm.getFlightList());
		flightsAvailable.addAll(flightsAvailableParm);

		// get flight at beginning of the route
		LinkedList<Flight> flightList = new LinkedList<Flight>();
		flightList = route.getFlightList();
		Flight beginningFlight = flightList.get(0);

		// trim flightsAvailableList for the details of this route
		List<Flight> flightsAvailableList = new ArrayList<Flight>();
		for (Flight flight : flightsAvailable) {
			if ((flight.getDeparture() + flight.getEta()) <= beginningFlight.getDeparture())
				flightsAvailableList.add(flight);
		}

		System.out.println("RouteDaoImpl.completeRoute-flightsAvailableList.size="
				+ flightsAvailableList.size());

		if (flightsAvailableList.size() == 0) {
			route.setRouteComplete(true);
			return route;
		} else {
			for (Flight flight : flightsAvailableList) {
				if (flight.getDestination().equals(beginningFlight.getOrigin())) {
					// add flight to route
					flightList = route.getFlightList();
					flightList.addFirst(flight);
					route.setFlightList(flightList);

					if (flight.getOrigin().equals(userOrigin)) {
						route.setRouteComplete(true);
						return route;
					} else {
						// create new route
						Route routeAdditional = new Route();
						LinkedList<Flight> flightListAdditional = new LinkedList<Flight>();
						routeAdditional.setFlightList(flightListAdditional);
						flightListAdditional.addAll(flightList);
						route = completeRoute(routeAdditional, flightsAvailableList);
						continue;
					}

				}

			}

		}
		return route;
	}

}
