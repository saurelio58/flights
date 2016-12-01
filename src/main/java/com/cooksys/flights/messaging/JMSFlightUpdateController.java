package com.cooksys.flights.messaging;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cooksys.core.models.Flight;

@Repository
public class JMSFlightUpdateController {

	@Autowired
	FlightChange flightChange;

	public Session session;
	private MessageConsumer consumer;
	private static final String TOPIC_NAME = "FlightUpdate";

	public JMSFlightUpdateController() {

		try {
			// Create a ConnectionFactory
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
					"tcp://localhost:61616");
			// Create a Connection
			Connection connection = connectionFactory.createConnection();
			connection.start();
			// Create a Session
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// Create the destination (Topic or Queue)
			Destination destination = session.createTopic(TOPIC_NAME);

			consumer = session.createConsumer(destination);

			consumer.setMessageListener(new MessageListener() {
				@Override
				public void onMessage(Message message) {
					// when we get a message from the JMS server
					try {
						if (message instanceof TextMessage) {
							TextMessage textMessage = (TextMessage) message;

							String text = textMessage.getText();
							String msgType = textMessage.getStringProperty("FlightStatus");
							if (msgType.equals("Flights Delayed")) {
//								System.out.println(
//										"JMSFlightUpdateController.onMessage-process Delayed");
								flightChange.delayed(text);

							} else {
								if (msgType.equals("Flight Arrived")) {
//									System.out.println(
//											"JMSFlightUpdateController.onMessage-process Arrived");
									flightChange.arrived(textMessage);
								} else {
									System.out.println(
											"JMSFlightUpdateController.onMessage-unexpected FlightStatus="
													+ msgType);
								}
							}

						} else {
							System.out.println(
									"JMSFlightUpdateController-Received unknown type: " + message);
						}
					} catch (Exception e) {
						System.out.println("JMSFlightUpdateController-failed in onMessage");
					}
				}
			});

		} catch (Exception e) {
			System.out.println("JMSFlightUpdateController-failed to reach JMS Server");
			System.out.println("JMSFlightUpdateController-Caught: " + e);
			e.printStackTrace();
		}

	}

}
