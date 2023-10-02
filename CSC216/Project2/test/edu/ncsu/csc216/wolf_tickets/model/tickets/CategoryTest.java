package edu.ncsu.csc216.wolf_tickets.model.tickets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class for the Category class
 * 
 * @author karth
 *
 */
public class CategoryTest {

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
	 * Tests for the getTicketsAsArray() method
	 */
	@Test
	public void testGetTicketsAsArray() {
		Ticket ticket = new Ticket(TICKET_NAME, TICKET_DESCRIPTION, ACTIVE);
		Category category = new Category("Web", 5);
		category.addTicket(ticket);
		category.getTicketsAsArray();
		String[][] tickets = new String[1][2];
		tickets[0][0] = 0 + "";
		tickets[0][1] = ticket.getTicketName();
		assertEquals(tickets[0][0], category.getTicketsAsArray()[0][0]);
		assertEquals(tickets[0][1], category.getTicketsAsArray()[0][1]);
	}

	/**
	 * Tests for the Category constructor
	 */
	@Test
	public void testCategory() {
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new Category(null, 10));
		assertEquals("Invalid name.", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> new Category("", 10));
		assertEquals("Invalid name.", e2.getMessage());
		Exception e3 = assertThrows(IllegalArgumentException.class, 
				() -> new Category("Classroom Tech", -1));
		assertEquals("Invalid completed count.", e3.getMessage());
		Category category = new Category("Classroom Tech", 10);
		assertEquals("Classroom Tech", category.getCategoryName());
		assertEquals(10, category.getCompletedCount());
	}

	/**
	 * Tests for the compareTo() method
	 */
	@Test
	public void testCompareTo() {
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new Category(null, 10));
		assertEquals("Invalid name.", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> new Category("", 10));
		assertEquals("Invalid name.", e2.getMessage());
		Exception e3 = assertThrows(IllegalArgumentException.class, 
				() -> new Category("Classroom Tech", -1));
		assertEquals("Invalid completed count.", e3.getMessage());
		Category category1 = new Category("Classroom Tech", 10);
		assertEquals("Classroom Tech", category1.getCategoryName());
		assertEquals(10, category1.getCompletedCount());
		Category category2 = new Category("Web", 5);
		assertEquals("Web", category2.getCategoryName());
		assertEquals(5, category2.getCompletedCount());
	}

}
