/**
 * 
 */
package com.services.ticketbooking.models;

import java.util.Calendar;

/**
 * @author bhanu
 *
 */
public class Seat {
	
	/**
	 * row id of the seat
	 */
	private int row;
	
	/**
	 * column id of the seat
	 */
	private int column;
	
	/**
	 * Status of the seat {vacant, on-hold, reserved}
	 */
	private String status;
	
	/**
	 * expire of the holding seat
	 */
	private Calendar holdExpire;	
	
	/**
	 * Customer email address to hold the seat
	 */
	private String customerEmail;
	
	/**
	 * Unique Seat hold id
	 */
	private int holdId;
	
	public Seat() {
		this.status = "vacant";
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * @param column the column to set
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the holdExpire
	 */
	public Calendar getHoldExpire() {
		return holdExpire;
	}

	/**
	 * @param holdExpire the holdExpire to set
	 */
	public void setHoldExpire(Calendar holdExpire) {
		this.holdExpire = holdExpire;
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

	/**
	 * @return the holdId
	 */
	public int getHoldId() {
		return holdId;
	}

	/**
	 * @param holdId the holdId to set
	 */
	public void setHoldId(int holdId) {
		this.holdId = holdId;
	}
	
	/**
	 * Release the seat after the seat expire time
	 */
	public void releaseHold() {
		if(holdExpire!=null) {
			Calendar currentTime = Calendar.getInstance();
			int diff = currentTime.compareTo(holdExpire);
			
			if(diff>0)
				this.status = "vacant";
		}
	}
	
}
