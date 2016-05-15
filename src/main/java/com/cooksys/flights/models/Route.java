package com.cooksys.flights.models;

import java.util.LinkedList;

public class Route<Flight> {

	private LinkedList<Flight> flightList = new LinkedList<Flight>();

	public LinkedList<Flight> getFlightList() {
		return flightList;
	}

	public void setFlightList(LinkedList<Flight> flightList) {
		this.flightList = flightList;
	}

	public void addFlight(Flight flight) {
		flightList.addFirst(flight);
	}

}
