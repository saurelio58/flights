package com.cooksys.flights.dao.impl;

import java.util.ArrayList;
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
	// private Location userOrigin;
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
		Location userOrigin;
		Location userDestination;

		// provide a routes list for routeModel - it will be null at this point
		List<Route> routeList = new ArrayList<Route>();
		routeModel.setRouteList(routeList);

		userOrigin = routeModel.getOrigin();
		userDestination = routeModel.getDestination();

		// TESTING - next line
		// flightModel = testFM;
		// // get FlightModel from service
		RestTemplate restTemplate = new RestTemplate();
		flightModel = restTemplate.getForObject(
				"http://localhost:8080/bc-final-webservice/getFlightModel", FlightModel.class);

		// copy all "valid" flights to a new array
		List<Flight> flightsAvailableList = new ArrayList<Flight>();
		for (Flight flight : flightModel.getFlights()) {
			if (flight.getDeparture() >= 0)
				flightsAvailableList.add(flight);
		}

		// put current day in routeModel for front end
		routeModel.setCurrentDay(flightModel.getCurrentDay());

		System.out.println("RouteDaoImpl.getFlightsForRoute-flightsAvailableList.size="
				+ flightsAvailableList.size());

		List<Flight> workingRoute = new ArrayList<Flight>();

		findRoutes(routeModel, flightsAvailableList, workingRoute, userOrigin, userDestination);

		return routeModel;

	}

	private void findRoutes(RouteModel routeModel, List<Flight> flightsAvailable,
			List<Flight> workingRouteParm, Location origin, Location destination) {

		for (Flight flight : flightsAvailable) {
			if (flight.getDestination().equals(destination)) {

				List<Flight> workingRoute = new ArrayList<Flight>();
				workingRoute.add(flight); // new flight goes in the front of
											// list
				workingRoute.addAll(workingRouteParm);

				if (flight.getOrigin().equals(origin)) {
					// we have a complete route
					Route routeNew = new Route();
					List<Flight> flightListNew = new ArrayList<Flight>();
					flightListNew.addAll(workingRoute);
					routeNew.setFlightList(flightListNew);
					routeNew.setRouteComplete(true);

					/////////////////
					routeNew.setDepartDay(flight.getDeparture());
					// get last FLight
					Flight lastFlight = flightListNew.get(flightListNew.size() - 1);
					routeNew.setArriveDay(lastFlight.getDeparture() + lastFlight.getEta());
					routeNew.setNumFlights(flightListNew.size());
					////////////////////
					
					// add the complete route to the routeList
					List<Route> routeList = new ArrayList<Route>();
					routeList = routeModel.getRouteList();
					routeList.add(routeNew);
					routeModel.setRouteList(routeList);
				} else {
					List<Flight> flightListNew = new ArrayList<Flight>();
					flightListNew = TrimFlightList(flightsAvailable, flight);
					findRoutes(routeModel, flightListNew, workingRoute, origin, flight.getOrigin());
				}

			}
		}

	}

	private List<Flight> TrimFlightList(List<Flight> flightList, Flight newFlight) {

		// trim flightsAvailableList for the details of this route
		List<Flight> flightListNew = new ArrayList<Flight>();

		for (Flight flight : flightList) {
			if ((flight.getDeparture() + flight.getEta()) <= newFlight.getDeparture())
				flightListNew.add(flight);
		}

//		System.out.println(
//				"RouteDaoImpl.TrimFlightList-flightsAvailableList.size=" + flightListNew.size());
		return flightListNew;
	}

}
