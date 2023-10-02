/**
 * 
 */
package edu.ncsu.csc216.wolf_tickets.model.tickets;

/**
 * Extends AbstractCategory to hold tickets in an ISwapList.
 * 
 * @author karth
 *
 */
public class Category extends AbstractCategory implements Comparable<Category> {

	/**
	 * Constructs a Category object
	 * 
	 * @param categoryName   the name of the category
	 * @param completedCount the total number of completed tickets
	 */
	public Category(String categoryName, int completedCount) {
		super(categoryName, completedCount);
	}

	/**
	 * Returns a 2D String array where the first column is the priority of the
	 * Ticket, which is the index of the ticket in the list of tickets, and the name
	 * of the Ticket.
	 * 
	 * @return tickets as a 2D array
	 */
	public String[][] getTicketsAsArray() {
		String[][] tickets = new String[getTickets().size()][2];
		for (int i = 0; i < getTickets().size(); i++) {
			tickets[i][0] = i + "";
			tickets[i][1] = getTicket(i).getTicketName();
		}
		return tickets;
	}

	/**
	 * Compares the names of the Categorys.
	 * 
	 * @param otherCategory the other category
	 * @return a value depending on how category compared to other categories
	 *         alphanumerically
	 */
	@Override
	public int compareTo(Category otherCategory) {
		if (super.getCategoryName().compareTo(otherCategory.getCategoryName()) != 0) {
			return super.getCategoryName().compareTo(otherCategory.getCategoryName());
		}
		return 0;
	}

}
