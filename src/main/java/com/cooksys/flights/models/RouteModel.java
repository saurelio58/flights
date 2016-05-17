package com.cooksys.flights.models;

import java.util.ArrayList;
import java.util.List;

import com.cooksys.core.models.Location;

public class RouteModel {

	private Location origin;
	private Location destination;
	private Integer currentDay;
	private List<Route> routeList;
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
	public Integer getCurrentDay() {
		return currentDay;
	}
	public void setCurrentDay(Integer currentDay) {
		this.currentDay = currentDay;
	}
	public List<Route> getRouteList() {
		return routeList;
	}
	public void setRouteList(List<Route> routeList) {
		this.routeList = routeList;
	}



}
