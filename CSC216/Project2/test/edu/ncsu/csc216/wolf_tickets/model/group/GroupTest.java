package edu.ncsu.csc216.wolf_tickets.model.group;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tickets.model.tickets.ActiveTicketList;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Category;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Ticket;

/**
 * Test class for the Group class
 * 
 * @author karth
 *
 */
public class GroupTest {

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
	 * Group name constant
	 */
	private static final String GROUP_NAME = "CSC IT";

	/**
	 * Tests the Group constructor
	 */
	@Test
	public void testGroup() {
		assertThrows(IllegalArgumentException.class, () -> new Group(null));
		assertThrows(IllegalArgumentException.class, () -> new Group(""));
		assertThrows(IllegalArgumentException.class, 
				() -> new Group(ActiveTicketList.ACTIVE_TASKS_NAME));
		Group group = new Group(GROUP_NAME);
		assertEquals(GROUP_NAME, group.getGroupName());
		assertEquals(1, group.getCategoriesNames().length);
		assertTrue(group.isChanged());
		assertEquals(0, group.getCurrentCategory().getTickets().size());
	}

	/**
	 * Tests the saveGroup() method
	 */
	@Test
	public void testSaveGroup() {
		String invalidTestFile = "test-files/group8.txt";
		String groupName = "CSC IT";
		Group group = new Group(groupName);
		File file1 = new File(invalidTestFile);
		Exception e = assertThrows(IllegalArgumentException.class, () -> group.saveGroup(file1));
		assertEquals("Unable to save file.", e.getMessage());
		String validTestFile = "test-files/actual_saveGroup.txt";
		File file2 = new File(validTestFile);
		group.addCategory(new Category("Web", 15));
		group.addTicket(new Ticket("Dr. McLeod website pages won't update.", "I recently uploaded"
				+ " new versions of pages on my website, but the changes don't show up when I go to"
				+ " the URL.", true));
		group.saveGroup(file2);
	}

	/**
	 * Tests the getGroupName() method
	 */
	@Test
	public void testGetGroupName() {
		assertThrows(IllegalArgumentException.class, () -> new Group(null));
		assertThrows(IllegalArgumentException.class, () -> new Group(""));
		assertThrows(IllegalArgumentException.class, 
				() -> new Group(ActiveTicketList.ACTIVE_TASKS_NAME));
		Group group = new Group(GROUP_NAME);
		assertEquals(GROUP_NAME, group.getGroupName());
		assertTrue(group.isChanged());
	}

	/**
	 * Tests the isChanged() method
	 */
	@Test
	public void testIsChanged() {
		assertThrows(IllegalArgumentException.class, () -> new Group(null));
		assertThrows(IllegalArgumentException.class, () -> new Group(""));
		assertThrows(IllegalArgumentException.class, 
				() -> new Group(ActiveTicketList.ACTIVE_TASKS_NAME));
		Group group = new Group(GROUP_NAME);
		assertEquals(GROUP_NAME, group.getGroupName());
		assertTrue(group.isChanged());
	}

	/**
	 * Tests the setChanged() method
	 */
	@Test
	public void testSetChanged() {
		assertThrows(IllegalArgumentException.class, () -> new Group(null));
		assertThrows(IllegalArgumentException.class, () -> new Group(""));
		assertThrows(IllegalArgumentException.class, 
				() -> new Group(ActiveTicketList.ACTIVE_TASKS_NAME));
		Group group = new Group(GROUP_NAME);
		assertEquals(GROUP_NAME, group.getGroupName());
		assertTrue(group.isChanged());
		group.setChanged(false);
		assertFalse(group.isChanged());
	}

	/**
	 * Tests the addCategory() method
	 */
	@Test
	public void testAddCategory() {
		assertThrows(IllegalArgumentException.class, () -> new Group(null));
		assertThrows(IllegalArgumentException.class, () -> new Group(""));
		assertThrows(IllegalArgumentException.class, 
				() -> new Group(ActiveTicketList.ACTIVE_TASKS_NAME));
		Group group = new Group(GROUP_NAME);
		assertEquals(GROUP_NAME, group.getGroupName());
		assertTrue(group.isChanged());
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> group.addCategory(new Category(ActiveTicketList.ACTIVE_TASKS_NAME, 5)));
		assertEquals("Invalid name.", e1.getMessage());
		group.addCategory(new Category("Web", 5));
		assertEquals("Active Tickets", group.getCategoriesNames()[0]);
		assertEquals("Web", group.getCurrentCategory().getCategoryName());
		Exception e = assertThrows(IllegalArgumentException.class, 
				() -> group.addCategory(new Category("Web", 5)));
		assertEquals("Invalid name.", e.getMessage());
		assertTrue(group.isChanged());
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> group.addCategory(new Category(ActiveTicketList.ACTIVE_TASKS_NAME, 5)));
		assertEquals("Invalid name.", e2.getMessage());
	}

	/**
	 * Tests the getCategoriesNames() method
	 */
	@Test
	public void testGetCategoriesNames() {
		assertThrows(IllegalArgumentException.class, () -> new Group(null));
		assertThrows(IllegalArgumentException.class, () -> new Group(""));
		assertThrows(IllegalArgumentException.class, 
				() -> new Group(ActiveTicketList.ACTIVE_TASKS_NAME));
		Group group = new Group(GROUP_NAME);
		assertEquals(GROUP_NAME, group.getGroupName());
		assertTrue(group.isChanged());
		group.addCategory(new Category("Web", 5));
		group.addCategory(new Category("Classroom Tech", 10));
		assertEquals("Active Tickets", group.getCategoriesNames()[0]);
		assertEquals(10, group.getCurrentCategory().getCompletedCount());
	}

	/**
	 * Tests the setCurrentCategory() method
	 */
	@Test
	public void testSetCurrentCategory() {
		Group group = new Group(GROUP_NAME);
		group.addCategory(new Category("Web", 5));
		group.setCurrentCategory("Web");
		assertEquals("Web", group.getCurrentCategory().getCategoryName());
	}

	/**
	 * Tests the getCurrentCategory() method
	 */
	@Test
	public void testGetCurrentCategory() {
		Group group = new Group(GROUP_NAME);
		group.setCurrentCategory(GROUP_NAME);
		assertEquals("Active Tickets", group.getCurrentCategory().getCategoryName());
	}

	/**
	 * Tests the editCategory() method
	 */
	@Test
	public void testEditCategory() {
		Group group = new Group(GROUP_NAME);
		group.addCategory(new Category("Web", 5));
		group.addCategory(new Category("Classroom Tech", 10));
		assertThrows(IllegalArgumentException.class, () -> group.editCategory("Web"));
		assertThrows(IllegalArgumentException.class, 
				() -> group.editCategory(ActiveTicketList.ACTIVE_TASKS_NAME));
		group.editCategory("Category 1");
		assertEquals("Category 1", group.getCurrentCategory().getCategoryName());
	}

	/**
	 * Tests the removeCategory() method
	 */
	@Test
	public void testRemoveCategory() {
		assertThrows(IllegalArgumentException.class, () -> new Group(null));
		assertThrows(IllegalArgumentException.class, () -> new Group(""));
		assertThrows(IllegalArgumentException.class, 
				() -> new Group(ActiveTicketList.ACTIVE_TASKS_NAME));
		Group group = new Group(GROUP_NAME);
		assertEquals(GROUP_NAME, group.getGroupName());
		assertTrue(group.isChanged());
		group.addCategory(new Category("Web", 5));
		group.addCategory(new Category("Classroom Tech", 10));
		assertEquals("Active Tickets", group.getCategoriesNames()[0]);
		assertEquals(10, group.getCurrentCategory().getCompletedCount());
		group.removeCategory();
		assertEquals(2, group.getCategoriesNames().length);
	}

	/**
	 * Tests the addTicket() method
	 */
	@Test
	public void testAddTicket() {
		assertThrows(IllegalArgumentException.class, () -> new Group(null));
		assertThrows(IllegalArgumentException.class, () -> new Group(""));
		assertThrows(IllegalArgumentException.class, 
				() -> new Group(ActiveTicketList.ACTIVE_TASKS_NAME));
		Group group = new Group(GROUP_NAME);
		assertEquals(GROUP_NAME, group.getGroupName());
		assertTrue(group.isChanged());
		group.addCategory(new Category("CSC IT", 5));
		Ticket ticket = new Ticket(TICKET_NAME, TICKET_DESCRIPTION, ACTIVE);
		group.addTicket(ticket);
		assertEquals(1, group.getCurrentCategory().getTickets().size());
	}

	/**
	 * Tests the editTicket() method
	 */
	@Test
	public void testEditTicket() {
		assertThrows(IllegalArgumentException.class, () -> new Group(null));
		assertThrows(IllegalArgumentException.class, () -> new Group(""));
		assertThrows(IllegalArgumentException.class, 
				() -> new Group(ActiveTicketList.ACTIVE_TASKS_NAME));
		Group group = new Group(GROUP_NAME);
		assertEquals(GROUP_NAME, group.getGroupName());
		assertTrue(group.isChanged());
		group.addCategory(new Category("Web", 5));
		Ticket ticket = new Ticket(TICKET_NAME, TICKET_DESCRIPTION, ACTIVE);
		group.addTicket(ticket);
		assertEquals(1, group.getCurrentCategory().getTickets().size());
		group.editTicket(0, "Ticket 1", "Description 1", ACTIVE);
		assertEquals("Ticket 1", group.getCurrentCategory().getTicket(0).getTicketName());
		assertEquals("Description 1", 
				group.getCurrentCategory().getTicket(0).getTicketDescription());
		assertEquals(ACTIVE, group.getCurrentCategory().getTicket(0).isActive());
	}

	/**
	 * Tests the addTicket() method
	 */
	@Test
	public void testAddTicket1() {
		Group group = new Group("Group1");
		group.addCategory(new Category("Category1", 0));
		assertTrue(group.isChanged());
		group.addTicket(new Ticket("Ticket1", "Ticket1Description", false));
		group.setCurrentCategory(ActiveTicketList.ACTIVE_TASKS_NAME);
		assertEquals(0, group.getCurrentCategory().getTicketsAsArray().length);
		group.setCurrentCategory("Category1");
		group.addTicket(new Ticket("Ticket2", "Ticket2Description", false));
		group.setCurrentCategory(ActiveTicketList.ACTIVE_TASKS_NAME);
		assertEquals(0, group.getCurrentCategory().getTicketsAsArray().length);
		group.setCurrentCategory("Category1");
		group.addTicket(new Ticket("Ticket3", "Ticket3Description", true));
		group.setCurrentCategory(ActiveTicketList.ACTIVE_TASKS_NAME);
		assertEquals(1, group.getCurrentCategory().getTicketsAsArray().length);
		group.setCurrentCategory("Category1");
		group.addTicket(new Ticket("Ticket4", "Ticket4Description", true));
		group.setCurrentCategory(ActiveTicketList.ACTIVE_TASKS_NAME);
		assertEquals(2, group.getCurrentCategory().getTicketsAsArray().length);
	}

	/**
	 * Tests the addTicket() method
	 */
	@Test
	public void testEditCategory1() {
		Group group = new Group("Group1");
		group.addCategory(new Category("Category1", 0));
		assertTrue(group.isChanged());
		group.addCategory(new Category("ACategory", 0));
		assertTrue(group.isChanged());
		group.addCategory(new Category("MiddleCategory", 0));
		assertTrue(group.isChanged());
		group.addCategory(new Category("ZZZCategory", 0));
		assertTrue(group.isChanged());
		assertEquals("ZZZCategory", group.getCategoriesNames()[4]);
	}

	/**
	 * Tests the addTicket() method
	 */
	@Test
	public void testScenario() {
		Group group = new Group("Group1");
		group.addCategory(new Category("Category1", 0));
		group.addTicket(new Ticket("Ticket1", "Ticket1Description", true));
		group.addTicket(new Ticket("Ticket2", "Ticket2Description", false));
		group.addTicket(new Ticket("Ticket3", "Ticket3Description", true));
		group.addCategory(new Category("ACategory", 0));
		group.addTicket(new Ticket("Ticket4", "Ticket41Description", true));
		group.addTicket(new Ticket("Ticket5", "Ticket5Description", false));
		assertTrue(group.isChanged());
		group.setCurrentCategory("ACategory");
		group.setCurrentCategory("Category1");
		group.setCurrentCategory(ActiveTicketList.ACTIVE_TASKS_NAME);
		group.getCurrentCategory().getTicket(1).completeTicket();
		group.setCurrentCategory(ActiveTicketList.ACTIVE_TASKS_NAME);
		group.setCurrentCategory("ACategory");
		group.setCurrentCategory("Category1");
		group.setCurrentCategory(ActiveTicketList.ACTIVE_TASKS_NAME);
		assertEquals(2, group.getCurrentCategory().getTicketsAsArray().length);
		assertEquals(1, group.getCurrentCategory().getCompletedCount());
	}

}
