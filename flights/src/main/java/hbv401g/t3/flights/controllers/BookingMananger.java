package controllers;

import java.time.LocalDateTime;
import java.util.Date;

import datastructures.Booking;
import datastructures.Person;
import datastructures.Flight;

public class BookingMananger {
  private Booking[] bookings;

  public BookingMananger() {
  }

  public Booking create(char s, int r, Person p, Flight flight) {
	  LocalDateTime now = LocalDateTime.now();
	  Booking booking = new Booking(s,r,p,flight,now);
	  return booking;
  }

  public void delete(Booking b) {
    
  }
}