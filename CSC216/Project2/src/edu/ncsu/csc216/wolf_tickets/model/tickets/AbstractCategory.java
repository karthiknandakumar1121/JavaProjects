/**
 * 
 */
package edu.ncsu.csc216.wolf_tickets.model.tickets;

import edu.ncsu.csc216.wolf_tickets.model.util.ISwapList;
import edu.ncsu.csc216.wolf_tickets.model.util.SwapList;

/**
 * The AbstractCategory class is an abstract class at the top of the hierarchy
 * for categories. The AbstractCategory knows its categoryName, the ISwapList of
 * Tickets, and the number of completed tickets.
 * 
 * @author karth
 *
 */
public abstract class AbstractCategory {

	/**
	 * The name of the category
	 */
	private String categoryName;

	/**
	 * The total number of completed tickets
	 */
	private int completedCount;

	/**
	 * A list of tickets
	 */
	private ISwapList<Ticket> tickets;

	/**
	 * Sets the fields from the parameters and constructs a SwapList for the
	 * Tickets.
	 * 
	 * @param categoryName   the name of the category
	 * @param completedCount the total number of completed tickets
	 * @throws IllegalArgumentException if the completedCount is less than zero
	 */
	public AbstractCategory(String categoryName, int completedCount) {
		setCategoryName(categoryName);
		tickets = new SwapList<Ticket>();
		if (completedCount < 0) {
			throw new IllegalArgumentException("Invalid completed count.");
		}
		this.completedCount = completedCount;
	}

	/**
	 * Returns the name of the category
	 * 
	 * @return the name of the category
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * Sets the name of the category
	 * 
	 * @param categoryName the name of the category
	 * @throws IllegalArgumentException if the category's name is null or empty
	 *                                  string
	 */
	public void setCategoryName(String categoryName) {
		if (categoryName == null || "".equals(categoryName)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		this.categoryName = categoryName;
	}

	/**
	 * Gets the list of tickets after a swipe is done
	 * 
	 * @return a list of tickets
	 */
	public ISwapList<Ticket> getTickets() {
		return tickets;
	}

	/**
	 * Returns the number of completed tickets
	 * 
	 * @return the number of completed tickets
	 */
	public int getCompletedCount() {
		return completedCount;
	}

	/**
	 * Adds the Ticket to the end of the list. The current instance of the Category
	 * adds itself to the Ticket.
	 * 
	 * @param t the ticket
	 */
	public void addTicket(Ticket t) {
		t.addCategory(this);
		tickets.add(t);
	}

	/**
	 * Removes the Ticket from the list of tickets and returns the removed ticket.
	 * 
	 * @param idx the index of the ticket
	 * @return the ticket
	 */
	public Ticket removeTicket(int idx) {
		Ticket ticket = tickets.remove(idx);
		return ticket;
	}

	/**
	 * Returns the Ticket at the given index.
	 * 
	 * @param idx the index of the ticket
	 * @return the ticket at the given index
	 */
	public Ticket getTicket(int idx) {
		return tickets.get(idx);
	}

	/**
	 * Finds the given Ticket in the list and removes it. The completedCount is
	 * incremented.
	 * 
	 * @param t a ticket
	 */
	public void completeTicket(Ticket t) {
		for (int i = 0; i < tickets.size(); i++) {
			if (tickets.get(i) == t) {
				tickets.remove(i);
				completedCount++;
				break;
			}
		}
	}

	/**
	 * An abstract method that returns a 2D String array. The contents of the array
	 * are left for the child classes to define.
	 * 
	 * @return array as a string
	 */
	public abstract String[][] getTicketsAsArray();
}
