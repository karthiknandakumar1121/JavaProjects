package edu.ncsu.csc216.wolf_tickets.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tickets.model.group.Group;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Category;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Ticket;

/**
 * Test class for the GroupReader class
 * 
 * @author karth
 *
 */
public class GroupReaderTest {

	/** valid file with only a group name */
	private final String validTestFile1 = "test-files/group0.txt";

	/** valid file with three categories */
	private final String validTestFile2 = "test-files/group1.txt";

	/** valid file with items that can be missing */
	private final String validTestFile3 = "test-files/group2.txt";

	/** missing leading ! in file - IAE thrown with message Unable to load file. */
	private String invalidTestFile = "test-files/group3.txt";

	/** category with missing name - creates group with no categories */
	private String validTestFile5 = "test-files/group5.txt";

	/**
	 * Tests the readGroupFile() method
	 */
	@Test
	public void testReadGroupFile() {
		// Testing invalid file for missing leading !
		File file1 = new File("test-files/group8.txt");
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> GroupReader.readGroupFile(file1));
		assertEquals("Unable to load file.", e1.getMessage());

		File file5 = new File(invalidTestFile);
		Exception e2 = assertThrows(IllegalArgumentException.class, 
				() -> GroupReader.readGroupFile(file5));
		assertEquals("Unable to load file.", e2.getMessage());

		// Testing valid file with only a group name
		File file2 = new File(validTestFile1);
		Group group0 = GroupReader.readGroupFile(file2);
		assertEquals("CSC IT", group0.getGroupName());

		// Testing valid file with three categories
		File file3 = new File(validTestFile2);
		Group group1 = GroupReader.readGroupFile(file3);
		assertEquals("CSC IT", group1.getGroupName());
		assertEquals("Active Tickets", group1.getCurrentCategory().getCategoryName());
		assertEquals(0, group1.getCurrentCategory().getCompletedCount());
		group1.getCurrentCategory()
				.addTicket(new Ticket("Dr. McLeod website pages won't update.",
						"I recently uploaded new versions of pages on my website, but the changes "
								+ "don't show up when I go to the URL.",
						true));
		assertEquals("EBII 1025 Laptop display won't work", 
				group1.getCurrentCategory().getTicket(0).getTicketName());
		assertTrue(group1.getCurrentCategory().getTicket(0).isActive());

		// Testing valid file with items that can be missing
		File file4 = new File(validTestFile3);
		Group group2 = GroupReader.readGroupFile(file4);
		assertEquals("CSC IT", group2.getGroupName());
		assertEquals("Active Tickets", group2.getCurrentCategory().getCategoryName());
		assertEquals(0, group2.getCurrentCategory().getCompletedCount());
		group2.addTicket(new Ticket("EBII 1025 Laptop display won't work",
				"The projector will not show my laptop's display in EBII 1025. Using the podium"
						+ " computer works fine. My laptop shows the extra display, but I only see "
						+ "a black screen on the classroom screen.",
				true));
		assertEquals("EBII 1025 Laptop display won't work", 
				group2.getCurrentCategory().getTicket(0).getTicketName());

		// Testing category without number of completed tasks
		File file6 = new File(validTestFile5);
		Group group3 = GroupReader.readGroupFile(file6);
		assertEquals("OIT", group3.getGroupName());
		group3.addCategory(new Category("License Renewal", 0));
		assertEquals("License Renewal", group3.getCurrentCategory().getCategoryName());
		assertEquals(0, group3.getCurrentCategory().getCompletedCount());
		group3.addTicket(new Ticket("Camtasia", "", true));
		assertEquals("Camtasia", group3.getCurrentCategory().getTicket(0).getTicketName());
	}

	/**
	 * Tests the readGroupFile() method
	 */
	@Test
	public void testValidGroup1() {
		File file = new File("test-files/group1.txt");
		Group group = GroupReader.readGroupFile(file);
		assertEquals(4, group.getCategoriesNames().length);
	}

	/**
	 * Tests the readGroupFile() method for invalid category name
	 */
	@Test
	public void testValidGroup5() {
		File file = new File("test-files/group5.txt");
		Group group = GroupReader.readGroupFile(file);
		assertEquals(1, group.getCategoriesNames().length);
	}

	/**
	 * Tests the readGroupFile() method for invalid category name
	 */
	@Test
	public void testValidGroup7() {
		File file = new File("test-files/group7.txt");
		Group group = GroupReader.readGroupFile(file);
		assertEquals("Active Tickets", group.getCategoriesNames()[0]);
		assertEquals(2, group.getCategoriesNames().length);
	}

	/**
	 * Tests the readGroupFile() method for negative completed count
	 */
	@Test
	public void testInvalidGroup6() {
		File file = new File("test-files/group6.txt");
		Group group = GroupReader.readGroupFile(file);
		assertEquals(0, group.getCurrentCategory().getCompletedCount());
	}

	/**
	 * Tests the readGroupFile() method
	 */
	@Test
	public void testInvalidGroup2() {
		File file = new File("test-files/group2.txt");
		Group group = GroupReader.readGroupFile(file);
		assertEquals(4, group.getCategoriesNames().length);
	}

	/**
	 * Tests the readGroupFile() method for file with no completed count for
	 * categories
	 */
	@Test
	public void testValidGroup4() {
		File file = new File("test-files/group4.txt");
		Group group = GroupReader.readGroupFile(file);
		assertEquals(1, group.getCategoriesNames().length);
	}
}
