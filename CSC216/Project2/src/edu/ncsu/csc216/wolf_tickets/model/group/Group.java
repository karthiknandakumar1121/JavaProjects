/**
 * 
 */
package edu.ncsu.csc216.wolf_tickets.model.group;

import java.io.File;

import edu.ncsu.csc216.wolf_tickets.model.io.GroupWriter;
import edu.ncsu.csc216.wolf_tickets.model.tickets.AbstractCategory;
import edu.ncsu.csc216.wolf_tickets.model.tickets.ActiveTicketList;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Category;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Ticket;
import edu.ncsu.csc216.wolf_tickets.model.util.ISortedList;
import edu.ncsu.csc216.wolf_tickets.model.util.ISwapList;
import edu.ncsu.csc216.wolf_tickets.model.util.SortedList;

/**
 * A Group has an ISortedList of Categorys, one ActiveTicketList, an
 * AbstractCategory for the currentCategory, a groupName, and boolean flag that
 * keeps track of if the Group has been changed since the last save.
 * 
 * @author karth
 *
 */
public class Group {

	/**
	 * The name of the group
	 */
	private String groupName;

	/**
	 * Status of the group
	 */
	private boolean isChanged;

	/**
	 * A SortedList of categories
	 */
	private ISortedList<Category> categories = null;

	/**
	 * A list of active tickets
	 */
	private ActiveTicketList activeTicketList = null;

	/**
	 * The current category displayed
	 */
	private AbstractCategory currentCategory = null;

	/**
	 * Constructs a Group with the given name. The categories field is constructed
	 * as a SortedList and the activeTicketList is constructed as an empty
	 * ActiveTicketList. The currentCategory is set to the activeTicketList.
	 * isChanged is initialized to true.
	 * 
	 * @throws IllegalArgumentException if the groupName is null, empty, or matches
	 *                                  ACTIVE_TASKS_NAME
	 * @param groupName the name of the group
	 */
	public Group(String groupName) {
		categories = new SortedList<Category>();
		activeTicketList = new ActiveTicketList();
		currentCategory = activeTicketList;
		isChanged = true;
		setGroupName(groupName);
	}

	/**
	 * Saves the current Group to the given file. isChanged is updated to false.
	 * 
	 * @param groupFile the group file
	 */
	public void saveGroup(File groupFile) {
		GroupWriter.writeGroupFile(groupFile, groupName, categories);
		isChanged = false;
	}

	/**
	 * Returns the name of the group
	 * 
	 * @return the name of the group
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * Sets the name of the group
	 * 
	 * @param groupName the name of the group
	 */
	private void setGroupName(String groupName) {
		if (groupName == null || "".equals(groupName) || 
				groupName.equals(ActiveTicketList.ACTIVE_TASKS_NAME)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		this.groupName = groupName;
	}

	/**
	 * Returns the status of the group
	 * 
	 * @return the status of the group
	 */
	public boolean isChanged() {
		return isChanged;
	}

	/**
	 * Changes the status of the group
	 * 
	 * @param changed status of the group
	 */
	public void setChanged(boolean changed) {
		this.isChanged = changed;
	}

	/**
	 * The Category is added to the list of categories (the SortedList will ensure
	 * sorted order), the current category is updated to the new category and
	 * isChanged is updated to true.
	 * 
	 * @param category a category
	 * 
	 * @throws IllegalArgumentException if the new Category’s name is
	 *                                  ACTIVE_TASKS_NAME or a duplicate of an
	 *                                  existing Category (both case insensitive)
	 */
	public void addCategory(Category category) {
		if (ActiveTicketList.ACTIVE_TASKS_NAME.equalsIgnoreCase(category.getCategoryName())) {
			throw new IllegalArgumentException("Invalid name.");
		}
		for (int i = 0; i < categories.size(); i++) {
			if (categories.get(i).getCategoryName().equalsIgnoreCase(category.getCategoryName())) {
				throw new IllegalArgumentException("Invalid name.");
			}
		}
		categories.add(category);
		currentCategory = category;
		isChanged = true;
	}

	/**
	 * Returns a list of category names
	 * 
	 * @return a list of category names
	 */
	public String[] getCategoriesNames() {
		String[] names = new String[categories.size() + 1];
		names[0] = "Active Tickets";
		for (int i = 1; i < names.length; i++) {
			names[i] = categories.get(i - 1).getCategoryName();
		}
		return names;
	}

	/**
	 * A private helper method that is useful for working with the ActiveTicketList.
	 * The order that Tickets are stored in the ActiveTicket list is related to the
	 * order of the Categorys and then the order of the active Tickets in those
	 * Categorys.
	 */
	private void getActiveTicketList() {
		activeTicketList.clearTickets();
		for (int i = 0; i < categories.size(); i++) {
			ISwapList<Ticket> tickets = categories.get(i).getTickets();
			for (int j = 0; j < tickets.size(); j++) {
				if (tickets.get(j).isActive()) {
					activeTicketList.addTicket(tickets.get(j));
				}
			}
		}
	}

	/**
	 * Sets the currentCategory to the AbstractCategory with the given name. If a
	 * Category with that name is not found, then the currentCategory is set to the
	 * activeTicketList.
	 * 
	 * @param categoryName name of the category
	 */
	public void setCurrentCategory(String categoryName) {
		boolean found = false;
		for (int i = 0; i < categories.size(); i++) {
			if (categories.get(i).getCategoryName().equals(categoryName)) {
				found = true;
				currentCategory = categories.get(i);
			}
		}
		if (!found) {
			getActiveTicketList();
			currentCategory = activeTicketList;
		}
	}

	/**
	 * Returns the current category
	 * 
	 * @return the current category
	 */
	public AbstractCategory getCurrentCategory() {
		return currentCategory;
	}

	/**
	 * Edits the currentCategory if the Category is not in place in the ISortedList
	 * while maintaing sorted order
	 * 
	 * @param categoryName the name of the category
	 * @throws IllegalArgumentException if the currentCategory is an
	 *                                  ActiveTicketList, if the new name matches
	 *                                  "Active Tickets" (case insensitive), or is a
	 *                                  duplicate of the name of another Category
	 *                                  (case insensitive and including if the name
	 *                                  is the same as the list that will be
	 *                                  renamed)
	 */
	public void editCategory(String categoryName) {
		if (currentCategory instanceof ActiveTicketList) {
			throw new IllegalArgumentException("The Active Tickets list may not be edited.");
		}
		if (categoryName.equals(ActiveTicketList.ACTIVE_TASKS_NAME)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		for (int i = 0; i < categories.size(); i++) {
			if (categories.get(i).getCategoryName().equalsIgnoreCase(categoryName)) {
				throw new IllegalArgumentException("Invalid name.");
			}
		}
		Category category = null;
		for (int i = 0; i < categories.size(); i++) {
			if (categories.get(i) == currentCategory) {
				category = categories.remove(i);
				break;
			}
		}
		if (category != null) {
			category.setCategoryName(categoryName);
		}
		categories.add(category);
		isChanged = true;
	}

	/**
	 * Removes the currentCategory and sets to the activeTicketList. isChanged is
	 * updated to true.
	 * 
	 * @throws IllegalArgumentException if the currentCategory is an
	 *                                  ActiveTicketList
	 */
	public void removeCategory() {
		if (currentCategory.equals(activeTicketList)) {
			throw new IllegalArgumentException("The Active Tickets list may not be deleted.");
		}
		for (int i = 0; i < categories.size(); i++) {
			if (categories.get(i) == currentCategory) {
				categories.remove(i);
				currentCategory = activeTicketList;
				break;
			}
		}
		isChanged = true;
	}

	/**
	 * A Ticket can only be added directly to a Category. If the currentCategory is
	 * not a Category do nothing with the Ticket. If the currentCategory is a
	 * Category, then add the ticket and check if the Ticket is active. If so,
	 * update the activeTicketList. isChanged is updated to true.
	 * 
	 * @param t the ticket
	 */
	public void addTicket(Ticket t) {
		boolean isCategory = false;
		if (currentCategory instanceof Category) {
			isCategory = true;
		}
		if (isCategory) {
			currentCategory.addTicket(t);
		} else {
			return;
		}
		if (t.isActive()) {
			getActiveTicketList();
		}
		isChanged = true;
	}

	/**
	 * A Ticket can only be edited if the currentCategory is a Category; otherwise,
	 * do nothing. If the Ticket can be edited, update the fields of the Ticket at
	 * the specified index. Check if the Ticket is active. If so, then update the
	 * activeTicketList. isChanged is updated to true.
	 * 
	 * @param idx               the index of the ticket
	 * @param ticketName        the name of the ticket
	 * @param ticketDescription the description of the ticket
	 * @param active            the status of the ticket
	 */
	public void editTicket(int idx, String ticketName, String ticketDescription, boolean active) {
		boolean isCategory = false;
		for (int i = 0; i < categories.size(); i++) {
			if (categories.get(i).equals(currentCategory)) {
				isCategory = true;
				break;
			}
		}
		if (isCategory) {
			currentCategory.getTicket(idx).setTicketName(ticketName);
			currentCategory.getTicket(idx).setTicketDescription(ticketDescription);
			currentCategory.getTicket(idx).setActive(active);
		} else {
			return;
		}
		if (currentCategory.getTicket(idx).isActive()) {
			getActiveTicketList();
		}
		isChanged = true;
	}
}
