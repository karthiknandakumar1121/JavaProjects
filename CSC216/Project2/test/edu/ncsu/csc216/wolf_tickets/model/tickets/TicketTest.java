package edu.ncsu.csc216.wolf_tickets.model.tickets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class for the Ticket class
 * 
 * @author karth
 *
 */
public class TicketTest {

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
	 * Tests for the Ticket constructor
	 */
	@Test
	public void testTicket() {
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Ticket(null, TICKET_DESCRIPTION, ACTIVE));
		assertEquals("Incomplete ticket information.", e1.getMessage());
		Exception e3 = assertThrows(IllegalArgumentException.class, 
				() -> new Ticket(TICKET_NAME, null, ACTIVE));
		assertEquals("Incomplete ticket information.", e3.getMessage());
		Ticket ticket = new Ticket(TICKET_NAME, TICKET_DESCRIPTION, ACTIVE);
		assertEquals(TICKET_NAME, ticket.getTicketName());
		assertEquals(TICKET_DESCRIPTION, ticket.getTicketDescription());
		assertTrue(ticket.isActive());
	}

	/**
	 * Tests for the getTicketName() method
	 */
	@Test
	public void testGetTicketName() {
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Ticket(null, TICKET_DESCRIPTION, ACTIVE));
		assertEquals("Incomplete ticket information.", e1.getMessage());
		Exception e3 = assertThrows(IllegalArgumentException.class, 
				() -> new Ticket(TICKET_NAME, null, ACTIVE));
		assertEquals("Incomplete ticket information.", e3.getMessage());
		Ticket ticket = new Ticket(TICKET_NAME, TICKET_DESCRIPTION, ACTIVE);
		assertEquals(TICKET_NAME, ticket.getTicketName());
		assertEquals(TICKET_DESCRIPTION, ticket.getTicketDescription());
		assertTrue(ticket.isActive());
	}

	/**
	 * Tests for the setTicketName() method
	 */
	@Test
	public void testSetTicketName() {
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Ticket(null, TICKET_DESCRIPTION, ACTIVE));
		assertEquals("Incomplete ticket information.", e1.getMessage());
		Exception e3 = assertThrows(IllegalArgumentException.class, 
				() -> new Ticket(TICKET_NAME, null, ACTIVE));
		assertEquals("Incomplete ticket information.", e3.getMessage());
		Ticket ticket = new Ticket(TICKET_NAME, TICKET_DESCRIPTION, ACTIVE);
		assertEquals(TICKET_NAME, ticket.getTicketName());
		assertEquals(TICKET_DESCRIPTION, ticket.getTicketDescription());
		assertTrue(ticket.isActive());
	}

	/**
	 * Tests for the getTicketDescription() method
	 */
	@Test
	public void testGetTicketDescription() {
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Ticket(null, TICKET_DESCRIPTION, ACTIVE));
		assertEquals("Incomplete ticket information.", e1.getMessage());
		Exception e3 = assertThrows(IllegalArgumentException.class, 
				() -> new Ticket(TICKET_NAME, null, ACTIVE));
		assertEquals("Incomplete ticket information.", e3.getMessage());
		Ticket ticket = new Ticket(TICKET_NAME, TICKET_DESCRIPTION, ACTIVE);
		assertEquals(TICKET_NAME, ticket.getTicketName());
		assertEquals(TICKET_DESCRIPTION, ticket.getTicketDescription());
		assertTrue(ticket.isActive());
	}

	/**
	 * Tests for the setTicketDescription() method
	 */
	@Test
	public void testSetTicketDescription() {
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Ticket(null, TICKET_DESCRIPTION, ACTIVE));
		assertEquals("Incomplete ticket information.", e1.getMessage());
		Exception e3 = assertThrows(IllegalArgumentException.class, 
				() -> new Ticket(TICKET_NAME, null, ACTIVE));
		assertEquals("Incomplete ticket information.", e3.getMessage());
		Ticket ticket = new Ticket(TICKET_NAME, TICKET_DESCRIPTION, ACTIVE);
		assertEquals(TICKET_NAME, ticket.getTicketName());
		assertEquals(TICKET_DESCRIPTION, ticket.getTicketDescription());
		assertTrue(ticket.isActive());
	}

	/**
	 * Tests for the isActive() method
	 */
	@Test
	public void testIsActive() {
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Ticket(null, TICKET_DESCRIPTION, ACTIVE));
		assertEquals("Incomplete ticket information.", e1.getMessage());
		Exception e3 = assertThrows(IllegalArgumentException.class, 
				() -> new Ticket(TICKET_NAME, null, ACTIVE));
		assertEquals("Incomplete ticket information.", e3.getMessage());
		Ticket ticket = new Ticket(TICKET_NAME, TICKET_DESCRIPTION, ACTIVE);
		assertEquals(TICKET_NAME, ticket.getTicketName());
		assertEquals(TICKET_DESCRIPTION, ticket.getTicketDescription());
		assertTrue(ticket.isActive());
	}

	/**
	 * Tests for the setActive() method
	 */
	@Test
	public void testSetActive() {
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Ticket(null, TICKET_DESCRIPTION, ACTIVE));
		assertEquals("Incomplete ticket information.", e1.getMessage());
		Exception e3 = assertThrows(IllegalArgumentException.class, 
				() -> new Ticket(TICKET_NAME, null, ACTIVE));
		assertEquals("Incomplete ticket information.", e3.getMessage());
		Ticket ticket = new Ticket(TICKET_NAME, TICKET_DESCRIPTION, ACTIVE);
		assertEquals(TICKET_NAME, ticket.getTicketName());
		assertEquals(TICKET_DESCRIPTION, ticket.getTicketDescription());
		assertTrue(ticket.isActive());
	}

	/**
	 * Tests for the getCategoryName() method
	 */
	@Test
	public void testGetCategoryName() {
		Category category = null;
		Ticket ticket = new Ticket(TICKET_NAME, TICKET_DESCRIPTION, ACTIVE);
		assertEquals("", ticket.getCategoryName());
		category = new Category("Web", 5);
		category.addTicket(ticket);
		assertEquals("Web", ticket.getCategoryName());
	}

	/**
	 * Tests for the addCategory() method
	 */
	@Test
	public void testAddCategory() {
		Ticket ticket = new Ticket(TICKET_NAME, TICKET_DESCRIPTION, ACTIVE);
		Exception e = assertThrows(IllegalArgumentException.class, 
				() -> ticket.addCategory(null));
		assertEquals("Incomplete ticket information.", e.getMessage());
		Category category = new Category("Web", 5);
		ticket.addCategory(category);
		assertEquals("Web", ticket.getCategoryName());
	}

	/**
	 * Tests for the completeTicket() method
	 */
	@Test
	public void testCompleteTicket() {
		Ticket ticket = new Ticket(TICKET_NAME, TICKET_DESCRIPTION, ACTIVE);
		Exception e = assertThrows(IllegalArgumentException.class, 
				() -> ticket.addCategory(null));
		assertEquals("Incomplete ticket information.", e.getMessage());
		Category category = new Category("Web", 5);
		ticket.addCategory(category);
		assertEquals("Web", ticket.getCategoryName());
		ticket.completeTicket();
		assertEquals(0, category.getTickets().size());
		assertEquals(5, category.getCompletedCount());
	}

	/**
	 * Tests for the toString() method
	 */
	@Test
	public void testToString() {
		Ticket ticket = new Ticket(TICKET_NAME, TICKET_DESCRIPTION, ACTIVE);
		assertEquals("* " + TICKET_NAME + ",active" + "\n" + TICKET_DESCRIPTION, ticket.toString());
		ticket.setActive(false);
		assertEquals("* " + TICKET_NAME + "\n" + TICKET_DESCRIPTION, ticket.toString());
	}

}
