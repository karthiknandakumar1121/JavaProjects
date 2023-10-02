package edu.ncsu.csc216.wolf_tickets.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tickets.model.group.Group;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Category;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Ticket;
import edu.ncsu.csc216.wolf_tickets.model.util.ISortedList;
import edu.ncsu.csc216.wolf_tickets.model.util.SortedList;

/**
 * Test class for the GroupWriter class
 * 
 * @author karth
 *
 */
public class GroupWriterTest {

	/** valid file with only a group name */
	private final String invalidTestFile = "test-files/group8.txt";

	/** group name */
	private final String groupName = "CSC IT";

	/** List of categories */
	private final ISortedList<Category> categories = new SortedList<Category>();

	/**
	 * Tests the writeGroupFile() method
	 */
	@Test
	public void testWriteGroupFile() {
		File file = new File(invalidTestFile);
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> GroupWriter.writeGroupFile(file, groupName, categories));
		assertEquals("Unable to save file.", e.getMessage());
		String validTestFile = "test-files/actual_saveGroup.txt";
		File file2 = new File(validTestFile);
		Group group = new Group(groupName);
		group.addCategory(new Category("Web", 15));
		group.addTicket(new Ticket("Dr. McLeod website pages won't update.", "I recently uploaded"
				+ " new versions of pages on my website, but the changes don't show up when I go to"
				+ " the URL.", true));
		group.saveGroup(file2);
	}

}
