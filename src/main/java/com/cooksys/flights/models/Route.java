package com.cooksys.flights.models;

import java.util.LinkedList;

public class Route<Flight> {

	private Boolean routeComplete = false;
	private LinkedList<Flight> flightList = new LinkedList<Flight>();
	
	public Boolean getRouteComplete() {
		return routeComplete;
	}
	public void setRouteComplete(Boolean routeComplete) {
		this.routeComplete = routeComplete;
	}
	public LinkedList<Flight> getFlightList() {
		return flightList;
	}
	public void setFlightList(LinkedList<Flight> flightList) {
		this.flightList = flightList;
	}

	

}
