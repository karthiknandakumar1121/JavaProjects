/**
 * 
 */
package edu.ncsu.csc216.wolf_tickets.model.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_tickets.model.group.Group;
import edu.ncsu.csc216.wolf_tickets.model.tickets.AbstractCategory;
import edu.ncsu.csc216.wolf_tickets.model.tickets.ActiveTicketList;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Category;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Ticket;

/**
 * Processes a file containing a group, zero to many categories, and zero to
 * many tickets in each category.
 * 
 * @author karth
 *
 */
public class GroupReader {

	/**
	 * Receives a File with the file name to read from
	 * 
	 * @param groupFile the group file
	 * @return a group with its categories and tickets
	 * @throws IllegalArgumentException if the file cannot be loaded because it
	 *                                  doesn’t exist or the first character in the
	 *                                  file is not!
	 * @throws NoSuchElementException   if an element cannot be found
	 */
	public static Group readGroupFile(File groupFile) {
		Scanner fileReader = null;
		String line = "";
		Group group = null;
		try {
			fileReader = new Scanner(new FileInputStream(groupFile));
			while (fileReader.hasNextLine()) {
				line += fileReader.nextLine() + "\n";
			}
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
		if (line.charAt(0) != '!') {
			throw new IllegalArgumentException("Unable to load file.");
		}
		Scanner fileScanner = new Scanner(line);
		group = new Group(fileScanner.nextLine().trim().substring(2));
		fileScanner.useDelimiter("\\r?\\n?[#]");
		while (fileScanner.hasNext()) {
			Category category = null;
			try {
				category = processCategory(fileScanner.next().trim());
				group.addCategory(category);
			} catch (IllegalArgumentException e) {
				// Comment
			}

		}
		fileScanner.close();
		group.setCurrentCategory(ActiveTicketList.ACTIVE_TASKS_NAME);
		group.setChanged(false);
		return group;
	}

	/**
	 * Processes a category from a string
	 * 
	 * @param categoryText a text with the category
	 * @return a Category object
	 * @throws NoSuchElementException if an element cannot be found
	 */
	private static Category processCategory(String categoryText) {
		String text = categoryText.trim();
		Scanner sc = new Scanner(text);
		sc.useDelimiter(",");
		String categoryName = "";
		int completedCount = 0;
		try {

			String categoryName1 = sc.nextLine().trim();
			categoryName = categoryName1.split(",")[0];
			if (categoryName == null || "".equals(categoryName)) {
				sc.close();
				throw new IllegalArgumentException();
			}
			if (!categoryName1.contains(",")) {
				sc.close();
				throw new IllegalArgumentException();
			} else {
				completedCount = Integer.parseInt(categoryName1.split(",")[1]);
			}
		} catch (NoSuchElementException e) {
			sc.close();
			return null;
		}
		Category category = new Category(categoryName, completedCount);
		sc.useDelimiter("\\r?\\n?[*]");
		while (sc.hasNext()) {
			try {
				category.addTicket(processTicket(category, sc.next()));
			} catch (IllegalArgumentException e) {
				// Comment
			}
		}
		sc.close();
		return category;
	}

	/**
	 * Processes a ticket from a string
	 * 
	 * @param category   a category
	 * @param ticketText a text with the category
	 * @return a Ticket object
	 * @throws NoSuchElementException if an element cannot be found
	 */
	private static Ticket processTicket(AbstractCategory category, String ticketText) {
		Scanner sc = new Scanner(ticketText);
		try {
			String ticketDescription = "";
			String line = sc.nextLine().trim();
			while (sc.hasNextLine()) {
				ticketDescription += sc.nextLine().trim() + "\n";
			}
			String[] splitString = line.split(",");
			String ticketName = "";
			if ("".equals(line.split(",")[0])) {
				sc.close();
				throw new IllegalArgumentException();
			} else {
				ticketName = line.split(",")[0];
			}
			if (ticketName != null || !"".equals(ticketName)) {
				boolean isActive = false;
				if (splitString.length >= 2 && splitString[1].equals("active")) {
					isActive = true;
				}
				sc.close();
				return new Ticket(ticketName, ticketDescription, isActive);
			} else {
				sc.close();
				throw new IllegalArgumentException("Incomplete ticket information.");
			}
		} catch (NoSuchElementException e) {
			sc.close();
		}
		return null;
	}
}
