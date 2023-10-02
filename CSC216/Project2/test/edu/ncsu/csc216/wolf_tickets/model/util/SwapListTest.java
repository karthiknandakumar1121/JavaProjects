package edu.ncsu.csc216.wolf_tickets.model.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class for the SwapList class
 * 
 * @author karth
 *
 */
public class SwapListTest {

	/**
	 * Tests for the SwapList() constructor
	 */
	@Test
	public void testSwapList() {
		SwapList<Integer> list1 = assertDoesNotThrow(() -> new SwapList<Integer>());
		assertEquals(0, list1.size());
	}

	/**
	 * Tests for the add() method
	 */
	@Test
	public void testAdd() {
		SwapList<Integer> list1 = assertDoesNotThrow(() -> new SwapList<Integer>());
		list1.add(5);
		assertEquals(5, list1.get(0));
		assertEquals(1, list1.size());
		list1.add(10);
		assertEquals(5, list1.get(0));
		assertEquals(10, list1.get(1));
		assertEquals(2, list1.size());
		list1.add(15);
		assertEquals(5, list1.get(0));
		assertEquals(10, list1.get(1));
		assertEquals(15, list1.get(2));
		assertEquals(3, list1.size());
		list1.add(20);
		assertEquals(5, list1.get(0));
		assertEquals(10, list1.get(1));
		assertEquals(15, list1.get(2));
		assertEquals(20, list1.get(3));
		assertEquals(4, list1.size());
		list1.add(25);
		assertEquals(5, list1.get(0));
		assertEquals(10, list1.get(1));
		assertEquals(15, list1.get(2));
		assertEquals(20, list1.get(3));
		assertEquals(25, list1.get(4));
		assertEquals(5, list1.size());

		Exception e = assertThrows(NullPointerException.class, () -> list1.add(null));
		assertEquals("Cannot add null element.", e.getMessage());

		// Test add with growArray
		SwapList<Integer> list2 = assertDoesNotThrow(() -> new SwapList<Integer>());
		list2.add(1);
		list2.add(2);
		list2.add(3);
		list2.add(4);
		list2.add(5);
		list2.add(6);
		list2.add(7);
		list2.add(8);
		list2.add(9);
		list2.add(10);
		assertDoesNotThrow(() -> list2.add(20));
		assertEquals(11, list2.size());
		assertEquals(20, list2.get(10));
		assertEquals(10, list2.get(9));
	}

	/**
	 * Tests for the remove() method
	 */
	@Test
	public void testRemove() {
		SwapList<Integer> list1 = new SwapList<Integer>();
		list1.add(5);
		list1.add(10);
		list1.add(15);
		list1.add(20);
		assertEquals(4, list1.size());

		Exception e1 = assertThrows(IndexOutOfBoundsException.class, () -> list1.remove(-1));
		Exception e2 = assertThrows(IndexOutOfBoundsException.class, () -> list1.remove(4));
		Exception e3 = assertThrows(IndexOutOfBoundsException.class, () -> list1.remove(5));
		assertEquals("Invalid index.", e1.getMessage());
		assertEquals("Invalid index.", e2.getMessage());
		assertEquals("Invalid index.", e3.getMessage());

		assertEquals(15, list1.remove(2));
		assertEquals(3, list1.size());
		assertEquals(5, list1.get(0));
		assertEquals(10, list1.get(1));
		assertEquals(20, list1.get(2));

		assertEquals(20, list1.remove(2));
		assertEquals(2, list1.size());
		assertEquals(5, list1.get(0));
		assertEquals(10, list1.get(1));

		assertEquals(5, list1.remove(0));
		assertEquals(1, list1.size());
		assertEquals(10, list1.get(0));

		assertEquals(10, list1.remove(0));
		assertEquals(0, list1.size());
		assertThrows(IndexOutOfBoundsException.class, () -> list1.remove(0));
	}

	/**
	 * Tests for the moveUp() method
	 */
	@Test
	public void testMoveUp() {
		SwapList<Integer> list1 = new SwapList<Integer>();
		list1.add(5);
		list1.add(10);
		list1.add(15);
		list1.add(20);
		assertEquals(10, list1.get(1));
		assertEquals(15, list1.get(2));
		list1.moveUp(2);
		assertEquals(15, list1.get(1));
		assertEquals(10, list1.get(2));
	}

	/**
	 * Tests for the moveDown() method
	 */
	@Test
	public void testMoveDown() {
		SwapList<Integer> list1 = new SwapList<Integer>();
		list1.add(5);
		list1.add(10);
		list1.add(15);
		list1.add(20);
		assertEquals(10, list1.get(1));
		assertEquals(15, list1.get(2));
		list1.moveDown(1);
		assertEquals(15, list1.get(1));
		assertEquals(10, list1.get(2));
	}

	/**
	 * Tests for the moveToFront() method
	 */
	@Test
	public void testMoveToFront() {
		SwapList<Integer> list1 = new SwapList<Integer>();
		list1.add(5);
		list1.add(10);
		list1.add(15);
		list1.add(20);
		assertEquals(5, list1.get(0));
		assertEquals(10, list1.get(1));
		assertEquals(15, list1.get(2));
		assertEquals(20, list1.get(3));
		list1.moveToFront(2);
		assertEquals(15, list1.get(0));
		assertEquals(5, list1.get(1));
		assertEquals(10, list1.get(2));
		assertEquals(20, list1.get(3));
	}

	/**
	 * Tests for the moveToBack() method
	 */
	@Test
	public void testMoveToBack() {
		SwapList<Integer> list1 = new SwapList<Integer>();
		list1.add(5);
		list1.add(10);
		list1.add(15);
		list1.add(20);
		assertEquals(5, list1.get(0));
		assertEquals(10, list1.get(1));
		assertEquals(15, list1.get(2));
		assertEquals(20, list1.get(3));
		list1.moveToBack(1);
		assertEquals(5, list1.get(0));
		assertEquals(15, list1.get(1));
		assertEquals(20, list1.get(2));
		assertEquals(10, list1.get(3));
	}

	/**
	 * Tests for the get() method
	 */
	@Test
	public void testGet() {
		SwapList<Integer> list1 = new SwapList<Integer>();
		list1.add(99);
		list1.add(23);
		assertEquals(99, list1.get(0));
		assertEquals(23, list1.get(1));
		Exception e1 = assertThrows(IndexOutOfBoundsException.class, () -> list1.get(-1));
		Exception e2 = assertThrows(IndexOutOfBoundsException.class, () -> list1.get(2));
		assertEquals("Invalid index.", e1.getMessage());
		assertEquals("Invalid index.", e2.getMessage());
	}

	/**
	 * Tests for the size() method
	 */
	@Test
	public void testSize() {
		SwapList<Integer> list1 = new SwapList<Integer>();
		list1.add(99);
		assertEquals(1, list1.size());
		list1.add(23);
		assertEquals(2, list1.size());
	}

}
