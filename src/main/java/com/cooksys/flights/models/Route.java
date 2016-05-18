package com.cooksys.flights.models;

import java.util.LinkedList;

public class Route<Flight> {

	private Boolean routeComplete = false;
	private Integer departDay;
	private Integer arriveDay;
	private Integer numFlights;
	private LinkedList<Flight> flightList = new LinkedList<Flight>();
	public Boolean getRouteComplete() {
		return routeComplete;
	}
	public void setRouteComplete(Boolean routeComplete) {
		this.routeComplete = routeComplete;
	}
	public Integer getDepartDay() {
		return departDay;
	}
	public void setDepartDay(Integer departDay) {
		this.departDay = departDay;
	}
	public Integer getArriveDay() {
		return arriveDay;
	}
	public void setArriveDay(Integer arriveDay) {
		this.arriveDay = arriveDay;
	}
	public Integer getNumFlights() {
		return numFlights;
	}
	public void setNumFlights(Integer numFlights) {
		this.numFlights = numFlights;
	}
	public LinkedList<Flight> getFlightList() {
		return flightList;
	}
	public void setFlightList(LinkedList<Flight> flightList) {
		this.flightList = flightList;
	}
	
	

}
