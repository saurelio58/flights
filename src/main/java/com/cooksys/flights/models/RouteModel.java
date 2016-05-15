package com.cooksys.flights.models;

import java.util.ArrayList;
import java.util.List;

import com.cooksys.core.models.Location;

public class RouteModel {

	private Location origin;
	private Location destination;
	private List<Route> routes;

	public Location getOrigin() {
		return origin;
	}

	public void setOrigin(Location origin) {
		this.origin = origin;
	}

	public Location getDestination() {
		return destination;
	}

	public void setDestination(Location destination) {
		this.destination = destination;
	}

	public List<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}

}
