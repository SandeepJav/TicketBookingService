/**
 * 
 */
package com.services.ticketbooking.services;

import com.services.ticketbooking.exceptions.NoAvaialbleSeatsException;
import com.services.ticketbooking.exceptions.SeatHoldExpiredException;
import com.services.ticketbooking.models.SeatHold;

/**
 * @author bhanu
 *
 */
public interface TicketService {
	
	/**
	* The number of seats in the venue that are neither held nor reserved
	*
	* @return the number of tickets available in the venue
	*/
	int numSeatsAvailable();
	
	/**
	* Find and hold the best available seats for a customer
	*
	* @param numSeats the number of seats to find and hold
	* @param customerEmail unique identifier for the customer
	* @return a SeatHold object identifying the specific seats and related
	information
	*/
	SeatHold findAndHoldSeats(int numSeats, String customerEmail) throws NoAvaialbleSeatsException;
	
	/**
	* Commit seats held for a specific customer
	*
	* @param seatHoldId the seat hold identifier
	* @param customerEmail the email address of the customer to which the
	seat hold is assigned
	* @return a reservation confirmation code
	*/
	String reserveSeats(int seatHoldId, String customerEmail) throws SeatHoldExpiredException;
	
	/**
	 * Release the seat which is on hold after certain time stamp.
	 */
	void releaseHoldSeats();
}
