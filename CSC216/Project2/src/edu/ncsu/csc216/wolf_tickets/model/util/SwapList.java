/**
 * 
 */
package edu.ncsu.csc216.wolf_tickets.model.util;

/**
 * Implements the ISwapList interface.
 * 
 * @author karth
 *
 * @param <E> type for the ISwapList
 */
public class SwapList<E> implements ISwapList<E> {

	/**
	 * Initial capacity of a SwapList
	 */
	private static final int INITIAL_CAPACITY = 10;

	/**
	 * An array of E type
	 */
	private E[] list;

	/**
	 * Size of a SwapList
	 */
	private int size;

	/**
	 * Constructor for SwapList class. Creates a new array of type E and sets the
	 * size to 0.
	 */
	@SuppressWarnings("unchecked")
	public SwapList() {
		list = (E[]) new Object[INITIAL_CAPACITY];
		size = 0;
	}

	/**
	 * Adds the element to the end of the list.
	 * 
	 * @param element element to add
	 * @throws NullPointerException if element is null
	 */
	@Override
	public void add(E element) {
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		size++;
		checkCapacity(size);
		list[size - 1] = element;
	}

	/**
	 * A private helper method for doubling the size of the array.
	 */
	@SuppressWarnings("unchecked")
	private void growArray() {
		E[] newArr = (E[]) new Object[size * 2];
		for (int i = 0; i < size; i++) {
			newArr[i] = list[i];
		}
		list = newArr;
	}

	/**
	 * Method to check capacity
	 * 
	 * @param idx index of a element
	 */
	private void checkCapacity(int idx) {
		if (idx >= INITIAL_CAPACITY) {
			growArray();
		}
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
		E removed = null;
		checkindex(idx);
		for (int i = idx; i < size(); i++) {
			if (i == idx) {
				removed = list[idx];
				list[idx] = null;
			}
			list[i] = list[i + 1];
		}
		size--;
		return removed;
	}

	/**
	 * Checks an index to see if it is within bounds
	 * 
	 * @param idx index of a element
	 * @throws IndexOutOfBoundsException if the given index is invalid
	 */
	private void checkindex(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
	}

	/**
	 * Moves the element at the given index to index-1. If the element is already at
	 * the front of the list, the list is not changed.
	 * 
	 * @param idx index of element to move up
	 * @throws IndexOutOfBoundsException if the idx is out of bounds for the list
	 */
	@Override
	public void moveUp(int idx) {
		checkindex(idx);
		if (idx != 0) {
			E temp = list[idx - 1];
			list[idx - 1] = list[idx];
			list[idx] = temp;
		}
	}

	/**
	 * Moves the element at the given index to index+1. If the element is already at
	 * the end of the list, the list is not changed.
	 * 
	 * @param idx index of element to move down
	 * @throws IndexOutOfBoundsException if the idx is out of bounds for the list
	 */
	@Override
	public void moveDown(int idx) {
		checkindex(idx);
		if (idx != size - 1) {
			E temp = list[idx + 1];
			list[idx + 1] = list[idx];
			list[idx] = temp;
		}
	}

	/**
	 * Moves the element at the given index to index 0. If the element is already at
	 * the front of the list, the list is not changed.
	 * 
	 * @param idx index of element to move to the front
	 * @throws IndexOutOfBoundsException if the idx is out of bounds for the list
	 */
	@Override
	public void moveToFront(int idx) {
		checkindex(idx);
		E temp = list[0];
		E value;
		list[0] = list[idx];
		for (int i = 1; i < list.length; i++) {
			if (i == idx + 1) {
				break;
			}
			value = list[i];
			list[i] = temp;
			temp = value;
		}
	}

	/**
	 * Moves the element at the given index to size-1. If the element is already at
	 * the end of the list, the list is not changed.
	 * 
	 * @param idx index of element to move to the back
	 * @throws IndexOutOfBoundsException if the idx is out of bounds for the list
	 */
	@Override
	public void moveToBack(int idx) {
		checkindex(idx);
		E temp = remove(idx);
		add(temp);
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
		for (int i = 0; i < size(); i++) {
			if (i == idx) {
				return list[i];
			}
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

}
