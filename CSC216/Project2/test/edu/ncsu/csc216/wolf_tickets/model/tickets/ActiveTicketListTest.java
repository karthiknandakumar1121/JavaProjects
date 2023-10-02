package edu.ncsu.csc216.wolf_tickets.model.tickets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class for the ActiveTicketList class
 * 
 * @author karth
 *
 */
public class ActiveTicketListTest {

	/**
	 * Constant ticket name
	 */
	private static final String TICKET_NAME = "Dr. McLeod website pages won't update.";

	/**
	 * Constant active ticket status
	 */
	private static final boolean ACTIVE = true;

	/**
	 * Constant ticket description
	 */
	private static final String TICKET_DESCRIPTION = "I recently uploaded new versions of pages on "
			+ "my website, but the changes don't show up when I go to the URL.";

	/**
	 * Tests for the setCategoryName() method
	 */
	@Test
	public void testSetCategoryName() {
		ActiveTicketList atl = new ActiveTicketList();
		Exception e = assertThrows(IllegalArgumentException.class, 
				() -> atl.setCategoryName("Category 1"));
		assertEquals("The Active Tickets list may not be edited.", e.getMessage());
		atl.setCategoryName(ActiveTicketList.ACTIVE_TASKS_NAME);
		assertEquals(ActiveTicketList.ACTIVE_TASKS_NAME, atl.getCategoryName());
	}

	/**
	 * Tests for the addTicket() method
	 */
	@Test
	public void testAddTicket() {
		ActiveTicketList atl = new ActiveTicketList();
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> atl.addTicket(new Ticket(TICKET_NAME, TICKET_DESCRIPTION, false)));
		assertEquals("Cannot add ticket to Active Tickets.", e.getMessage());
		Ticket ticket = new Ticket(TICKET_NAME, TICKET_DESCRIPTION, ACTIVE);
		atl.addTicket(ticket);
		assertEquals("Dr. McLeod website pages won't update.", atl.getTicket(0).getTicketName());
	}

	/**
	 * Tests for the getTicketsAsArray() method
	 */
	@Test
	public void testGetTicketsAsArray() {
		ActiveTicketList atl = new ActiveTicketList();
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> atl.addTicket(new Ticket(TICKET_NAME, TICKET_DESCRIPTION, false)));
		assertEquals("Cannot add ticket to Active Tickets.", e.getMessage());
		Ticket ticket = new Ticket(TICKET_NAME, TICKET_DESCRIPTION, ACTIVE);
		atl.addTicket(ticket);
		assertEquals("Dr. McLeod website pages won't update.", atl.getTicket(0).getTicketName());
		atl.getTicketsAsArray();
		assertEquals(ActiveTicketList.ACTIVE_TASKS_NAME, atl.getTicketsAsArray()[0][0]);
		assertEquals(TICKET_NAME, atl.getTicket(0).getTicketName());
	}

	/**
	 * Tests for the ActiveTicketList constructor
	 */
	@Test
	public void testActiveTicketList() {
		ActiveTicketList atl = new ActiveTicketList();
		assertEquals(ActiveTicketList.ACTIVE_TASKS_NAME, atl.getCategoryName());
		assertEquals(0, atl.getCompletedCount());
	}

	/**
	 * Tests for the clearTickets() method
	 */
	@Test
	public void testClearTickets() {
		ActiveTicketList atl = new ActiveTicketList();
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> atl.addTicket(new Ticket(TICKET_NAME, TICKET_DESCRIPTION, false)));
		assertEquals("Cannot add ticket to Active Tickets.", e.getMessage());
		Ticket ticket = new Ticket(TICKET_NAME, TICKET_DESCRIPTION, ACTIVE);
		atl.addTicket(ticket);
		assertEquals("Dr. McLeod website pages won't update.", atl.getTicket(0).getTicketName());
		atl.clearTickets();
		assertEquals(0, atl.getTickets().size());
	}

}
