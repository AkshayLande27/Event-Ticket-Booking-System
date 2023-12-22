package com.ekaki.demo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event {
	int event_id;
	String event_name;
	LocalDate event_date;
	LocalTime event_time;
	int venue_id;
	String description;
	
	
	public Event() {
		super();
	}
	
	public Event(int event_id, String event_name, LocalDate event_date, LocalTime event_time, int venue_id,
			String description) {
		super();
		this.event_id = event_id;
		this.event_name = event_name;
		this.event_date = event_date;
		this.event_time = event_time;
		this.venue_id = venue_id;
		this.description = description;
	}

	public int getEvent_id() {
		return event_id;
	}

	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}

	public String getEvent_name() {
		return event_name;
	}

	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}

	public LocalDate getEvent_date() {
		return event_date;
	}

	public void setEvent_date(LocalDate event_date) {
		this.event_date = event_date;
	}

	public LocalTime getEvent_time() {
		return event_time;
	}

	public void setEvent_time(LocalTime event_time) {
		this.event_time = event_time;
	}

	public int getVenue_id() {
		return venue_id;
	}

	public void setVenue_id(int venue_id) {
		this.venue_id = venue_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	

}
