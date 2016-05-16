package com.cooksys.flights.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cooksys.flights.dao.TripDao;
import com.cooksys.flights.dao.UserDao;
import com.cooksys.flights.models.RouteModel;
import com.cooksys.flights.models.User;

@Repository
@Transactional
public class TripDaoImpl implements TripDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public User addTrip(RouteModel route) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getTrip(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
