/**
 * 
 */
package com.services.ticketbooking.exceptions;

/**
 * @author ak185292
 *
 */
public class SeatHoldExpiredException extends Exception {
	String str;
	
	
	/**
	 * @param str
	 */
	public SeatHoldExpiredException(String str) {
		super();
		this.str = str;
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Sorry..! Seats Hold are expired..!" + str;
	}
}
