package edu.ncsu.csc216.wolf_tickets.model.tickets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class for the AbstractCategory class
 * 
 * @author karth
 *
 */
public class AbstractCategoryTest {

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
	 * Tests for the AbstractCategory constructor
	 */
	@Test
	public void testAbstractCategory() {
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new Category(null, 10));
		assertEquals("Invalid name.", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> new Category("", 10));
		assertEquals("Invalid name.", e2.getMessage());
		Exception e3 = assertThrows(IllegalArgumentException.class, 
				() -> new Category("Classroom Tech", -1));
		assertEquals("Invalid completed count.", e3.getMessage());
		AbstractCategory abstractCategory = new Category("Classroom Tech", 10);
		assertEquals("Classroom Tech", abstractCategory.getCategoryName());
		assertEquals(10, abstractCategory.getCompletedCount());
	}

	/**
	 * Tests for the getCategoryName() method
	 */
	@Test
	public void testGetCategoryName() {
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new Category(null, 10));
		assertEquals("Invalid name.", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> new Category("", 10));
		assertEquals("Invalid name.", e2.getMessage());
		Exception e3 = assertThrows(IllegalArgumentException.class, 
				() -> new Category("Classroom Tech", -1));
		assertEquals("Invalid completed count.", e3.getMessage());
		AbstractCategory abstractCategory = new Category("Classroom Tech", 10);
		assertEquals("Classroom Tech", abstractCategory.getCategoryName());
		assertEquals(10, abstractCategory.getCompletedCount());
	}

	/**
	 * Tests for the setCategoryName() method
	 */
	@Test
	public void testSetCategoryName() {
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new Category(null, 10));
		assertEquals("Invalid name.", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> new Category("", 10));
		assertEquals("Invalid name.", e2.getMessage());
		Exception e3 = assertThrows(IllegalArgumentException.class, 
				() -> new Category("Classroom Tech", -1));
		assertEquals("Invalid completed count.", e3.getMessage());
		AbstractCategory abstractCategory = new Category("Classroom Tech", 10);
		assertEquals("Classroom Tech", abstractCategory.getCategoryName());
		assertEquals(10, abstractCategory.getCompletedCount());
	}

	/**
	 * Tests for the getTickets() method
	 */
	@Test
	public void testGetTickets() {
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new Category(null, 10));
		assertEquals("Invalid name.", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> new Category("", 10));
		assertEquals("Invalid name.", e2.getMessage());
		Exception e3 = assertThrows(IllegalArgumentException.class, 
				() -> new Category("Classroom Tech", -1));
		assertEquals("Invalid completed count.", e3.getMessage());
		AbstractCategory abstractCategory = new Category("Classroom Tech", 10);
		assertEquals("Classroom Tech", abstractCategory.getCategoryName());
		assertEquals(10, abstractCategory.getCompletedCount());
		Ticket ticket = new Ticket(TICKET_NAME, TICKET_DESCRIPTION, ACTIVE);
		abstractCategory.addTicket(ticket);
		assertEquals(ticket, abstractCategory.getTickets().get(0));
	}

	/**
	 * Tests for the getCompletedCount() method
	 */
	@Test
	public void testGetCompletedCount() {
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new Category(null, 10));
		assertEquals("Invalid name.", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> new Category("", 10));
		assertEquals("Invalid name.", e2.getMessage());
		Exception e3 = assertThrows(IllegalArgumentException.class, 
				() -> new Category("Classroom Tech", -1));
		assertEquals("Invalid completed count.", e3.getMessage());
		AbstractCategory abstractCategory = new Category("Classroom Tech", 10);
		assertEquals("Classroom Tech", abstractCategory.getCategoryName());
		assertEquals(10, abstractCategory.getCompletedCount());
	}

	/**
	 * Tests for the addTicket() method
	 */
	@Test
	public void testAddTicket() {
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new Category(null, 10));
		assertEquals("Invalid name.", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> new Category("", 10));
		assertEquals("Invalid name.", e2.getMessage());
		Exception e3 = assertThrows(IllegalArgumentException.class, 
				() -> new Category("Classroom Tech", -1));
		assertEquals("Invalid completed count.", e3.getMessage());
		AbstractCategory abstractCategory = new Category("Classroom Tech", 10);
		assertEquals("Classroom Tech", abstractCategory.getCategoryName());
		assertEquals(10, abstractCategory.getCompletedCount());
		Ticket ticket = new Ticket(TICKET_NAME, TICKET_DESCRIPTION, ACTIVE);
		abstractCategory.addTicket(ticket);
		assertEquals(ticket, abstractCategory.getTicket(0));
	}

	/**
	 * Tests for the removeTicket() method
	 */
	@Test
	public void testRemoveTicket() {
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new Category(null, 10));
		assertEquals("Invalid name.", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> new Category("", 10));
		assertEquals("Invalid name.", e2.getMessage());
		Exception e3 = assertThrows(IllegalArgumentException.class, 
				() -> new Category("Classroom Tech", -1));
		assertEquals("Invalid completed count.", e3.getMessage());
		AbstractCategory abstractCategory = new Category("Classroom Tech", 10);
		assertEquals("Classroom Tech", abstractCategory.getCategoryName());
		assertEquals(10, abstractCategory.getCompletedCount());
		Ticket ticket = new Ticket(TICKET_NAME, TICKET_DESCRIPTION, ACTIVE);
		abstractCategory.addTicket(ticket);
		assertEquals(ticket, abstractCategory.getTicket(0));
		abstractCategory.removeTicket(0);
		assertEquals(0, abstractCategory.getTickets().size());
	}

	/**
	 * Tests for the getTicket() method
	 */
	@Test
	public void testGetTicket() {
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new Category(null, 10));
		assertEquals("Invalid name.", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> new Category("", 10));
		assertEquals("Invalid name.", e2.getMessage());
		Exception e3 = assertThrows(IllegalArgumentException.class, 
				() -> new Category("Classroom Tech", -1));
		assertEquals("Invalid completed count.", e3.getMessage());
		AbstractCategory abstractCategory = new Category("Classroom Tech", 10);
		assertEquals("Classroom Tech", abstractCategory.getCategoryName());
		assertEquals(10, abstractCategory.getCompletedCount());
		Ticket ticket = new Ticket(TICKET_NAME, TICKET_DESCRIPTION, ACTIVE);
		abstractCategory.addTicket(ticket);
		assertEquals(ticket, abstractCategory.getTicket(0));
	}

	/**
	 * Tests for the completeTikcet() method
	 */
	@Test
	public void testCompleteTicket() {
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new Category(null, 10));
		assertEquals("Invalid name.", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> new Category("", 10));
		assertEquals("Invalid name.", e2.getMessage());
		Exception e3 = assertThrows(IllegalArgumentException.class, 
				() -> new Category("Classroom Tech", -1));
		assertEquals("Invalid completed count.", e3.getMessage());
		AbstractCategory abstractCategory = new Category("Classroom Tech", 10);
		assertEquals("Classroom Tech", abstractCategory.getCategoryName());
		assertEquals(10, abstractCategory.getCompletedCount());
		Ticket ticket = new Ticket(TICKET_NAME, TICKET_DESCRIPTION, ACTIVE);
		abstractCategory.addTicket(ticket);
		abstractCategory.completeTicket(ticket);
		assertEquals(0, abstractCategory.getTickets().size());
	}

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

}
