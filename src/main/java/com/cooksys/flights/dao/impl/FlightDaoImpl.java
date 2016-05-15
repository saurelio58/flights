package com.cooksys.flights.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cooksys.flights.dao.FlightDao;
import com.cooksys.flights.models.RouteModel;

@Repository
@Transactional
public class FlightDaoImpl implements FlightDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public RouteModel getFlightsForRoute(RouteModel routeModel) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
