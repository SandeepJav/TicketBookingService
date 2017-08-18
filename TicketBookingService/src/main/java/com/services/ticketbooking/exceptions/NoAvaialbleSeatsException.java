/**
 * 
 */
package com.services.ticketbooking.exceptions;

/**
 * @author bhanu
 *
 */
public class NoAvaialbleSeatsException extends Exception {

	String str;
	
		
	/**
	 * @param str
	 */
	public NoAvaialbleSeatsException(String str) {
		super();
		this.str = str;
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Sorry..! Seats are either on Hold or Reserved..!" + str;
	}
}
