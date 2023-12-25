package com.ekaki.demo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event {
	int event_id;
	String event_name;
	LocalDate event_date;
	LocalTime event_time;
	double ticketPrice;
	int venue_id;
	String description;
	int available_seats;
	String imagePath;
	String Venue;
	
	
	public Event() {
		super();
	}
	
	public Event( String event_name, String event_date, String event_time, double tp, int venue_id,
	        String description, int as, String ip, String v) {
	    super();
	    this.event_name = event_name;
	    // Parse the String date to LocalDate
	    this.event_date = LocalDate.parse(event_date);
	    // Parse the String time to LocalTime
	    this.event_time = LocalTime.parse(event_time);
	    this.ticketPrice = tp;
	    this.venue_id = venue_id;
	    this.description = description;
	    this.available_seats = as;
	    this.imagePath = ip;
	    this.Venue = v;
	}


	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public int getAvailable_seats() {
		return available_seats;
	}

	public void setAvailable_seats(int available_seats) {
		this.available_seats = available_seats;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getVenue() {
		return Venue;
	}

	public void setVenue(String venue) {
		Venue = venue;
	}

	public String getImagepath() {
		return imagePath;
	}

	public void setImagepath(String imagepath) {
		this.imagePath = imagepath;
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
