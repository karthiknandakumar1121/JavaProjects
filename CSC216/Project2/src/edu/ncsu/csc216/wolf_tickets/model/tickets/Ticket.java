/**
 * 
 */
package edu.ncsu.csc216.wolf_tickets.model.tickets;

import edu.ncsu.csc216.wolf_tickets.model.util.ISwapList;
import edu.ncsu.csc216.wolf_tickets.model.util.SwapList;

/**
 * Represents a ticket in the WolfTickets system.
 * 
 * @author karth
 *
 */
public class Ticket {

	/**
	 * The name of the ticket
	 */
	private String ticketName;

	/**
	 * The description of the ticket
	 */
	private String ticketDescription;

	/**
	 * Inidcation of an active ticket
	 */
	private boolean active;

	/**
	 * A list of categories
	 */
	private ISwapList<AbstractCategory> categories = null;

	/**
	 * Constructs the Ticket with the given parameters. The categories field is
	 * constructed to an empty SwapList of AbstractCategorys.
	 * 
	 * @param ticketName        the name of the ticket
	 * @param ticketDescription the description of the ticket
	 * @param active            indication of an active ticket
	 */
	public Ticket(String ticketName, String ticketDescription, boolean active) {
		setTicketName(ticketName);
		setTicketDescription(ticketDescription);
		setActive(active);
		categories = new SwapList<AbstractCategory>();
	}

	/**
	 * Returns the name of the ticket.
	 * 
	 * @return the name of the ticket
	 */
	public String getTicketName() {
		return ticketName;
	}

	/**
	 * Public helper method that checks for a valid ticket name.
	 * 
	 * @param ticketName the name of the ticket
	 * @throws IllegalArgumentException if ticket information is null or incomplete
	 */
	public void setTicketName(String ticketName) {
		if (ticketName == null || "".equals(ticketName)) {
			throw new IllegalArgumentException("Incomplete ticket information.");
		}
		this.ticketName = ticketName;
	}

	/**
	 * Returns the description of the ticket
	 * 
	 * @return the description of the ticket
	 */
	public String getTicketDescription() {
		return ticketDescription;
	}

	/**
	 * Public helper method that checks for a valid ticket description.
	 * 
	 * @param ticketDescription the description of the ticket
	 * @throws IllegalArgumentException if ticket information is incomplete
	 */
	public void setTicketDescription(String ticketDescription) {
		if (ticketDescription == null) {
			throw new IllegalArgumentException("Incomplete ticket information.");
		}
		this.ticketDescription = ticketDescription;
	}

	/**
	 * Returns whether or not a ticket is active
	 * 
	 * @return if a ticket is active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Sets the status of a ticket
	 * 
	 * @param active status of a ticket
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Returns the name of the category
	 * 
	 * @return name of the category
	 */
	public String getCategoryName() {
		if (categories == null || categories.size() == 0) {
			return "";
		}
		return categories.get(0).getCategoryName();
	}

	/**
	 * If the AbstractCategory is NOT already registered with the Ticket the
	 * AbstractCategory is added to the end of the categories field.
	 * 
	 * @param category the category
	 * @throws IllegalArgumentException if ticket information is incopmplete
	 */
	public void addCategory(AbstractCategory category) {
		if (category == null) {
			throw new IllegalArgumentException("Incomplete ticket information.");
		}
		boolean isFound = false;
		for (int i = 0; i < categories.size(); i++) {
			if (category == categories.get(i)) {
				isFound = true;
			}
		}
		if (!isFound) {
			categories.add(category);
		}
	}

	/**
	 * This method will complete the Ticket and notify the categories by sharing the
	 * current Ticket instance via the Category.completeTicket(Ticket) method.
	 */
	public void completeTicket() {
		for (int i = 0; i < categories.size(); i++) {
			categories.get(i).completeTicket(this);
		}
	}

	/**
	 * Returns a string representation of the Ticket for printing to a file.
	 */
	@Override
	public String toString() {
		if (!active) {
			return "* " + ticketName + "\n" + ticketDescription;
		}
		return "* " + ticketName + ",active" + "\n" + ticketDescription;
	}
}
