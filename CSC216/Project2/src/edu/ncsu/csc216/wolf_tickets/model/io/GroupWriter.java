/**
 * 
 */
package edu.ncsu.csc216.wolf_tickets.model.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

import edu.ncsu.csc216.wolf_tickets.model.tickets.Category;
import edu.ncsu.csc216.wolf_tickets.model.util.ISortedList;

/**
 * Writes the group’s Categorys to the given file.
 * 
 * @author karth
 *
 */
public class GroupWriter {

	/**
	 * Receives a File with the file name to write to, the name of the group, and a
	 * ISortedList of Categories to write to file and writes the data to the file
	 * 
	 * @param groupFile  the group file
	 * @param groupName  the name of the group
	 * @param categories a list of categories
	 * @throws IllegalArgumentException if there are any errors or exceptions
	 */
	public static void writeGroupFile(File groupFile, String groupName, 
			ISortedList<Category> categories) {
		if (groupFile.exists()) {
			try {
				PrintStream fileWriter = new PrintStream(new FileOutputStream(groupFile));
				fileWriter.println("! " + groupName);
				for (int i = 0; i < categories.size(); i++) {
					fileWriter.println(
							"# " + categories.get(i).getCategoryName() + "," + 
									categories.get(i).getCompletedCount());
					for (int j = 0; j < categories.get(i).getTickets().size(); j++) {
						fileWriter.println(categories.get(i).getTickets().get(j).toString());
					}
				}
				fileWriter.close();
			} catch (Exception e) {
				throw new IllegalArgumentException("Unable to save file.");
			}
		} else {
			throw new IllegalArgumentException("Unable to save file.");
		}
	}
}
