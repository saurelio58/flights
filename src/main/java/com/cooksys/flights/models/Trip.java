package com.cooksys.flights.models;
// Generated May 14, 2016 2:36:02 PM by Hibernate Tools 4.3.1.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Trip generated by hbm2java
 */
@Entity
@Table(name = "trip", catalog = "flight")
public class Trip implements java.io.Serializable {

	private Integer tripId;
	private String tripName;
	private String confirmationId;
	private Date lastUpdated;
	private Set<Segment> segments = new HashSet<Segment>(0);
	private Set<User> users = new HashSet<User>(0);

	public Trip() {
	}

	public Trip(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Trip(String tripName, String confirmationId, Date lastUpdated, Set<Segment> segments,
			Set<User> users) {
		this.tripName = tripName;
		this.confirmationId = confirmationId;
		this.lastUpdated = lastUpdated;
		this.segments = segments;
		this.users = users;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "trip_id", unique = true, nullable = false)
	public Integer getTripId() {
		return this.tripId;
	}

	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}

	@Column(name = "trip_name", length = 45)
	public String getTripName() {
		return this.tripName;
	}

	public void setTripName(String tripName) {
		this.tripName = tripName;
	}

	@Column(name = "confirmation_id", length = 6)
	public String getConfirmationId() {
		return this.confirmationId;
	}

	public void setConfirmationId(String confirmationId) {
		this.confirmationId = confirmationId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_updated", nullable = false, length = 19)
	public Date getLastUpdated() {
		return this.lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "trip_segment", catalog = "flight", uniqueConstraints = {
			@UniqueConstraint(columnNames = "flight_id"),
			@UniqueConstraint(columnNames = "trip_id") }, joinColumns = {
					@JoinColumn(name = "trip_id", unique = true, nullable = false, updatable = false) }, inverseJoinColumns = {
							@JoinColumn(name = "flight_id", unique = true, nullable = false, updatable = false) })
	public Set<Segment> getSegments() {
		return this.segments;
	}

	public void setSegments(Set<Segment> segments) {
		this.segments = segments;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "trips")
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}