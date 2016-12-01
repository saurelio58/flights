package com.cooksys.flights.messaging;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cooksys.core.models.Flight;
import com.cooksys.flights.models.Segment;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

@Repository
@Transactional
public class FlightChange {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void delayed(String text) {
		Gson gson = new Gson();
		List<Flight> flightList = new ArrayList<Flight>();
		Set<Segment> segments = new HashSet<Segment>();

		// get list of delayed flights -> flightList
		try {
			JSONArray jsonArray = new JSONArray(text);
			for (int i = 0; i < jsonArray.length(); i++) {
				Flight flight = gson.fromJson(jsonArray.get(i).toString(), Flight.class);
				flightList.add(flight);
			}
		} catch (Exception e) {
			System.out.println("FlightChange.delayed-unexpect error using JSON");
		}

		// find all matching flights in the segment table and change
		// flight_status to 'D'
		Integer flt;
		Character flag = 'D';
		for (Flight flight : flightList) {
			flt = flight.getFlightId();
			updateFlights(flt, flag);
		}

	}

	public void arrived(TextMessage textMessage) throws JsonSyntaxException, JMSException {

		Gson gson = new Gson();
		Flight flight = gson.fromJson(textMessage.getText(), Flight.class);
		Integer flt = flight.getFlightId();

		// find all matching flights in the segment table and change
		// flight_status to 'A'
		Character flag = 'A';
		updateFlights(flt, flag);

	}

	private void updateFlights(Integer flt, Character flag) {

		List<Segment> segmentList = new ArrayList<Segment>();
		Session session = getSession();

		try {
			segmentList = session.createQuery("from Segment where flightNumber = :flt")
					.setInteger("flt", flt).list();

			for (Segment segment : segmentList) {
				segment.setFlightStatus(flag);
				session.saveOrUpdate(segment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
