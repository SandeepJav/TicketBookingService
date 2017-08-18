/**
 * 
 */
package com.services.ticketbooking.impl;

import com.services.ticketbooking.exceptions.NoAvaialbleSeatsException;
import com.services.ticketbooking.exceptions.SeatHoldExpiredException;
import com.services.ticketbooking.models.Seat;
import com.services.ticketbooking.models.SeatHold;
import com.services.ticketbooking.services.TicketService;

import java.util.Calendar;

/**
 * @author bhanu
 *
 */
public class TicketServiceImpl implements TicketService {

	/**
	 * Number of rows in venue
	 */
	int numRows = 10;

	/**
	 * Number of columns in each row
	 */
	int[] numCol = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	/**
	 * Defines stadium without specifying # of columns
	 */
	Seat[][] venue = new Seat[numRows][];

	/**
	 * Defines number of seconds to get this seat to be expired
	 */
	private static final int SEAT_EXPIRY_SEC = 5;

	public TicketServiceImpl() {

		for (int i = 0; i < venue.length; i++) {
			for (int j = 0; j < numCol.length; j++) {
				venue[i] = new Seat[numCol.length];

			}
		}

		for (int i = 0; i < venue.length; i++) {
			for (int j = 0; j < numCol.length; j++) {
				Seat seater = new Seat();
				seater.setRow(i);
				seater.setColumn(j);
				venue[i][j] = seater;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ticket.booking.service.interfaces.TicketService#numSeatsAvailable()
	 */
	public int numSeatsAvailable() {
		releaseHoldSeats();
		int numSeatsAvailable = 0;

		for (int i = 0; i < venue.length; i++) {
			for (int j = 0; j < numCol.length; j++) {
				Seat seater = venue[i][j];
				if ("vacant".equalsIgnoreCase(seater.getStatus()))
					numSeatsAvailable++;
			}
		}

		return numSeatsAvailable;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ticket.booking.service.interfaces.TicketService#findAndHoldSeats(int,
	 * java.lang.String)
	 */
	public SeatHold findAndHoldSeats(int numSeats, String customerEmail) throws NoAvaialbleSeatsException {
		releaseHoldSeats();
		SeatHold seatHold = new SeatHold(numSeats, customerEmail);
		int availableSeats = numSeatsAvailable();
		if(availableSeats >= numSeats) {
			for (int i = 0; i < venue.length; i++) {
				for (int j = 0; j < numCol.length && numSeats > 0; j++) {
					Seat seater = venue[i][j];
						if ("vacant".equalsIgnoreCase(seater.getStatus())) {
							seater.setHoldId(seatHold.getSeatHoldId());
							seater.setStatus("on-hold");
							seater.setCustomerEmail(seatHold.getCustomerEmail());
							Calendar seatholdtime=Calendar.getInstance();
							seatholdtime.add(Calendar.SECOND, SEAT_EXPIRY_SEC);
							seater.setHoldExpire(seatholdtime);
							numSeats--;
						}
					}
			
			
			}
		}else
			throw new NoAvaialbleSeatsException("No Seat to Hold");
		
		return seatHold;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ticket.booking.service.interfaces.TicketService#reserveSeats(int,
	 * java.lang.String)
	 */
	public String reserveSeats(int seatHoldId, String customerEmail) throws SeatHoldExpiredException   {
		releaseHoldSeats();
		String confirmationCode = null;
		
		int availableSeatHold = availableSeatsOnHold(seatHoldId);
		
		if(availableSeatHold>0) {
			for (int i = 0; i < venue.length; i++) {
				for (int j = 0; j < numCol.length; j++) {
					Seat seater = venue[i][j];
					if (seater.getHoldId() == seatHoldId) {
						seater.setStatus("reserved");
						confirmationCode = String.valueOf(seater.getHoldId());
					}

				}
			}
		}else
			throw new SeatHoldExpiredException("Requested seats are expired..!!");
		
		return confirmationCode;
	}

	/**
	 * @param seatHoldId
	 * @return
	 */
	public int availableSeatsOnHold(int seatHoldId) {
		int availableSeatHold = 0;
		for (int i = 0; i < venue.length; i++) {
			for (int j = 0; j < numCol.length; j++) {
				Seat seater = venue[i][j];
				if (seater.getHoldId() == seatHoldId) {
					availableSeatHold++;
				}

			}
		}
		return availableSeatHold;
	}

	public void releaseHoldSeats() {
		for (int i = 0; i < venue.length; i++) {
			for (int j = 0; j < numCol.length; j++) {
				Seat seater = venue[i][j];
				seater.releaseHold();
			}
		}

	}
}
