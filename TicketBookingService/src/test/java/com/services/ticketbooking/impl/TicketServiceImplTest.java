package com.services.ticketbooking.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.services.ticketbooking.exceptions.NoAvaialbleSeatsException;
import com.services.ticketbooking.exceptions.SeatHoldExpiredException;
import com.services.ticketbooking.impl.TicketServiceImpl;
import com.services.ticketbooking.models.SeatHold;
import com.services.ticketbooking.services.TicketService;

/**
 * @author bhanu
 *
 */
public class TicketServiceImplTest {

	/**
	 * Initialization of the implementation class
	 */
	TicketService tsi;

	@Before
	public void init() {
		tsi = new TicketServiceImpl();
	}

	/**
	 * Test total available seats before booking, when seats are on hold, when seats
	 * are reserved, when seats are hold and on reserved
	 * 
	 * @throws NoAvaialbleSeatsException,Exception
	 */

	@Test
	public void testNumSeatsAvailable() throws NoAvaialbleSeatsException, Exception {
		// Before any seat booking
		assertEquals(100, tsi.numSeatsAvailable());

		// Holding for 5 seats
		SeatHold seatHold1 = tsi.findAndHoldSeats(5, "sandeep@gmail.com");
		assertEquals(95, tsi.numSeatsAvailable());

		Thread.sleep(6000);
		// Request for a seat hold followed by reservation of the seat
		SeatHold seatHold2 = tsi.findAndHoldSeats(10, "sandeep@gmail.com");
		tsi.reserveSeats(seatHold2.getSeatHoldId(), "sandeep@gmail.com");
		assertEquals(90, tsi.numSeatsAvailable());

	}

	/**
	 * Test Find and Hold Seat functionality
	 * 
	 * @throws NoAvaialbleSeatsException
	 */
	@Test
	public void testFindAndHoldSeats() throws NoAvaialbleSeatsException {
		SeatHold seatHold = tsi.findAndHoldSeats(5, "sandeep@gmail.com");

		assertEquals("sandeep@gmail.com", seatHold.getCustomerEmail());
		assertEquals(5, seatHold.getNumSeats());

		assertEquals(95, tsi.numSeatsAvailable());

	}

	/**
	 * Test Find and Hold Seat functionality where there are no availability of the seats during seat hold functionality
	 * @throws NoAvaialbleSeatsException
	 */
	@Test(expected = NoAvaialbleSeatsException.class)
	public void testFindAndHoldSeatsNoAvaialabieSeats() throws NoAvaialbleSeatsException {
		SeatHold seatHold1 = tsi.findAndHoldSeats(5, "sandeep@gmail.com");

		assertEquals("sandeep@gmail.com", seatHold1.getCustomerEmail());
		assertEquals(5, seatHold1.getNumSeats());

		assertEquals(95, tsi.numSeatsAvailable());
		
		SeatHold seatHold2 = tsi.findAndHoldSeats(100, "bhanu@gmail.com");

		assertEquals("bhanu@gmail.com", seatHold2.getCustomerEmail());
		assertEquals(100, seatHold2.getNumSeats());

		assertEquals(0, tsi.numSeatsAvailable());
	}

	/**
	 * Test reservation of the seat
	 * 
	 * @throws NoAvaialbleSeatsException
	 * @throws SeatHoldExpiredException 
	 * @throws Exception 
	 */
	@Test
	public void testReserveSeatsSeatHoldExpired() throws NoAvaialbleSeatsException, SeatHoldExpiredException, Exception {
		SeatHold seatHold = tsi.findAndHoldSeats(1, "bhanu@gmail.com");

		assertEquals("bhanu@gmail.com", seatHold.getCustomerEmail());
		assertEquals(1, seatHold.getNumSeats());
		assertEquals(99, tsi.numSeatsAvailable());
		
		Thread.sleep(6000);
		
		assertEquals(String.valueOf(seatHold.getSeatHoldId()),
				tsi.reserveSeats(seatHold.getSeatHoldId(), "bhanu@gmail.com"));
		
		assertEquals(100, tsi.numSeatsAvailable());

	}
	
	/**
	 * Test reservation of the seat
	 * 
	 * @throws NoAvaialbleSeatsException
	 * @throws SeatHoldExpiredException 
	 */
	@Test
	public void testReserveSeats() throws NoAvaialbleSeatsException, SeatHoldExpiredException {
		SeatHold seatHold = tsi.findAndHoldSeats(1, "bhanu@gmail.com");

		assertEquals("bhanu@gmail.com", seatHold.getCustomerEmail());
		assertEquals(1, seatHold.getNumSeats());

		assertEquals(99, tsi.numSeatsAvailable());
		assertEquals(String.valueOf(seatHold.getSeatHoldId()),
				tsi.reserveSeats(seatHold.getSeatHoldId(), "bhanu@gmail.com"));

	}

	/**
	 * Test reservation of the seat after the seat hold expiration
	 * 
	 * @throws NoAvaialbleSeatsException,
	 *             Exception
	 */
	@Test
	public void testReserveSeatsAfterSeatHoldExpiry() throws NoAvaialbleSeatsException, Exception {
		SeatHold seatHold1 = tsi.findAndHoldSeats(100, "sandeep@gmail.com");
		assertEquals(0, tsi.numSeatsAvailable());

		Thread.sleep(6000);

		SeatHold seatHold2 = tsi.findAndHoldSeats(50, "bhanu@gmail.com");
		assertEquals(50, tsi.numSeatsAvailable());

	}

}
