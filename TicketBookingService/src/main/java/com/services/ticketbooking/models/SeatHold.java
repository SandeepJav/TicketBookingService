/**
 * 
 */
package com.services.ticketbooking.models;

/**
 * @author bhanu
 *
 */
public class SeatHold {

	/**
	* Properity holds Seat Hold Id
	*/
	private int seatHoldId;
	
	/**
	* Number of seats requested for holding or reservation at given point of time.
	*/
	private int numSeats;
	
	/**
	* Customer Email Address who is holding or reserving the seat
	*/
	private String customerEmail;
	
	
	/**
     * Constructor.
     *
     * @param numSeats the number of seats to be hold.
     * @param customerEmail the email address of the customer who requested for the seats
     */
	public SeatHold(int numSeats, String customerEmail) {
		super();
		this.seatHoldId = hashCode();
		this.numSeats = numSeats;
		this.customerEmail = customerEmail;
	}


	/**
	 * @return the seatHoldId
	 */
	public int getSeatHoldId() {
		return seatHoldId;
	}


	/**
	 * @param seatHoldId the seatHoldId to set
	 */
	public void setSeatHoldId(int seatHoldId) {
		this.seatHoldId = seatHoldId;
	}


	/**
	 * @return the numSeats
	 */
	public int getNumSeats() {
		return numSeats;
	}


	/**
	 * @param numSeats the numSeats to set
	 */
	public void setNumSeats(int numSeats) {
		this.numSeats = numSeats;
	}


	/**
	 * @return the customerEmail
	 */
	public String getCustomerEmail() {
		return customerEmail;
	}


	/**
	 * @param customerEmail the customerEmail to set
	 */
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}	
}
