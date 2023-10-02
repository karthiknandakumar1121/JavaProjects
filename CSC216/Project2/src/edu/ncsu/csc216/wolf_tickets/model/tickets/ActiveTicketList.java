/**
 * 
 */
package edu.ncsu.csc216.wolf_tickets.model.tickets;

/**
 * Extends AbstractCategory to hold active tickets in an ISwapList.
 * 
 * @author karth
 *
 */
public class ActiveTicketList extends AbstractCategory {
	/**
	 * Constant holding the name of the "Active Tickets" list.
	 */
	public static final String ACTIVE_TASKS_NAME = "Active Tickets";

	/**
	 * Constructs the ActiveTicketList with the expected name and no completed
	 * tickets.
	 */
	public ActiveTicketList() {
		super(ACTIVE_TASKS_NAME, 0);
	}

	/**
	 * Overrides the method to check that the Ticket is active before adding to the
	 * end of the ISwapList.
	 * 
	 * @param t a ticket
	 * @throws IllegalArgumentException if the Ticket is not active
	 */
	@Override
	public void addTicket(Ticket t) {
		if (!t.isActive()) {
			throw new IllegalArgumentException("Cannot add ticket to Active Tickets.");
		}
		super.addTicket(t);
	}

	/**
	 * Overrides the method to ensure that the paramter value matches the expected
	 * name of "Active Tickets". If so, the name is set.
	 * 
	 * @param categoryName the name of the category
	 * @throws IllegalArgumentException if paramter value does not matches the
	 *                                  expected name of "Active Tickets".
	 */
	@Override
	public void setCategoryName(String categoryName) {
		if (!categoryName.equals(ACTIVE_TASKS_NAME)) {
			throw new IllegalArgumentException("The Active Tickets list may not be edited.");
		}
		super.setCategoryName(categoryName);
	}

	/**
	 * Returns a 2D String array where the first column is the name of the Category
	 * that the Ticket belongs to (or at least the Category at index 0) and the name
	 * of the Ticket.
	 * 
	 * @return tickets as a 2D array
	 */
	@Override
	public String[][] getTicketsAsArray() {
		String[][] tickets = new String[super.getTickets().size()][2];
		for (int i = 0; i < tickets.length; i++) {
			tickets[i][0] = super.getTicket(i).getCategoryName();
			tickets[i][1] = super.getTicket(i).getTicketName();
		}
		return tickets;
	}

	/**
	 * Clears the ActiveTicketList of all Tickets.
	 */
	public void clearTickets() {
		for (int i = getTickets().size() - 1; i >= 0; i--) {
			super.removeTicket(i);
		}
	}
}
