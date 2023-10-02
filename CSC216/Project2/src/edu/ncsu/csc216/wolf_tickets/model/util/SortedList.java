/**
 * 
 */
package edu.ncsu.csc216.wolf_tickets.model.util;

/**
 * Implements the ISortedList interface.
 * 
 * @author karth
 * @param <E> type for ISortedList; must implement Comparable
 */
public class SortedList<E extends Comparable<E>> implements ISortedList<E> {

	/**
	 * Size of the SortedList
	 */
	private int size;

	/**
	 * A list of ListNode type
	 */
	private ListNode front = null;

	/**
	 * Constructor for the SortedList class
	 */
	public SortedList() {
		size = 0;
	}

	/**
	 * Adds the element to the list in sorted order.
	 * 
	 * @param element element to add
	 * @throws NullPointerException     if element is null
	 * @throws IllegalArgumentException if element cannot be added
	 */
	@Override
	public void add(E element) {
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		if (front == null || element.compareTo(front.data) < 0) {
			front = new ListNode(element, front);
		} else {
			ListNode current = front;
			while (current != null) {
				if (current.data.compareTo(element) == 0) {
					throw new IllegalArgumentException("Cannot add duplicate element.");
				}
				current = current.next;
			}
			current = front;
			while (current.next != null && current.next.data.compareTo(element) < 0) {
				current = current.next;
			}
			current.next = new ListNode(element, current.next);
		}
		size++;
	}

	/**
	 * Returns the element from the given index. The element is removed from the
	 * list.
	 * 
	 * @param idx index to remove element from
	 * @return element at given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds for the list
	 */
	@Override
	public E remove(int idx) {
		checkindex(idx);
		ListNode previousNode = null;
		ListNode currentNode = front;
		E removed = null;
		for (int i = 0; i < size; i++) {
			if (i == idx) {
				removed = currentNode.data;
				if (previousNode != null) {
					previousNode.next = currentNode.next;
				} else {
					front = currentNode.next;
				}
				size--;
				return removed;
			}
			previousNode = currentNode;
			currentNode = currentNode.next;
		}
		return null;
	}

	/**
	 * Checks an index to see if it is within bounds
	 * 
	 * @param idx index of the element
	 */
	private void checkindex(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
	}

	/**
	 * Returns true if the element is in the list.
	 * 
	 * @param element element to search for
	 * @return true if element is found
	 */
	@Override
	public boolean contains(E element) {
		ListNode currentNode = front;
		while (currentNode != null) {
			if (element.equals(currentNode.data)) {
				return true;
			}
			currentNode = currentNode.next;
		}
		return false;
	}

	/**
	 * Returns the element at the given index.
	 * 
	 * @param idx index of the element to retrieve
	 * @return element at the given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds for the list
	 */
	@Override
	public E get(int idx) {
		checkindex(idx);
		ListNode currentNode = front;
		for (int i = 0; i < size; i++) {
			if (i == idx) {
				return currentNode.data;
			}
			currentNode = currentNode.next;
		}
		return null;
	}

	/**
	 * Returns the number of elements in the list.
	 * 
	 * @return number of elements in the list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * ListNode to represent information associated with a single element
	 * 
	 * @author karth
	 *
	 */
	private class ListNode {
		/** The data the list node contains */
		public E data;

		/** The next node in the list */
		public ListNode next;

		/**
		 * Constructor for the ListNode class which sets a next ListNode.
		 * 
		 * @param data The data the ListNode contains.
		 * @param next The next node in the list.
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}
}
