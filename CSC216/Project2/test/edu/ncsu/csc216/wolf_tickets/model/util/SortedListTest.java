package edu.ncsu.csc216.wolf_tickets.model.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class for the SortedList class
 * 
 * @author karth
 *
 */
public class SortedListTest {

	/**
	 * Tests for the SortedList() constructor
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));

		list.add("orange");
		list.add("banana");
		list.add("kiwi");
		list.add("avocado");
		list.add("pear");
		list.add("apple");
		list.add("lemon");
		list.add("strawberry");
		list.add("lime");
		list.add("grapefruit");
		list.add("watermelon");
		assertEquals(11, list.size());
	}

	/**
	 * Tests for the add() method
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();

		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));

		list.add("apple");
		list.add("avocado");
		list.add("watermelon");
		assertEquals("apple", list.get(0));
		assertEquals("avocado", list.get(1));
		assertEquals("watermelon", list.get(3));

		try {
			list.add(null);
		} catch (NullPointerException e) {
			assertEquals(4, list.size());
		}

		try {
			list.add("apple");
		} catch (IllegalArgumentException e) {
			assertEquals(4, list.size());
		}
	}

	/**
	 * Tests for the remove() method
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();

		try {
			list.remove(0);
		} catch (IndexOutOfBoundsException ioobe) {
			assertEquals(0, list.size());
		}

		list.add("Qiap");
		list.add("Qiao");
		list.add("Chao");
		list.add("Aaron");
		list.add("Cathy");
		list.add("Alice");
		list.add("Julia");
		list.add("Jessica");
		assertEquals(8, list.size());

		try {
			list.remove(-1);
		} catch (IndexOutOfBoundsException ioobe) {
			assertEquals(8, list.size());
		}

		try {
			list.remove(list.size());
		} catch (IndexOutOfBoundsException ioobe) {
			assertEquals(8, list.size());
		}

		list.remove(4);
		assertEquals(7, list.size());

		list.remove(6);
		assertEquals(6, list.size());

		list.remove(0);
		assertEquals(5, list.size());

		list.remove(4);
		assertEquals(4, list.size());
	}

	/**
	 * Tests for the contains() method
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();

		try {
			list.get(5);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}

		list.add("apple");
		list.add("banana");
		assertTrue(list.contains("apple"));
		assertFalse(list.contains("orange"));
	}

	/**
	 * Tests for the get() method
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();

		try {
			list.get(5);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}

		list.add("apple");
		list.add("banana");

		try {
			list.get(-1);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(2, list.size());
		}

		try {
			list.get(10);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(2, list.size());
		}
	}

	/**
	 * Tests for the size() method
	 */
	@Test
	public void testSize() {
		SortedList<String> list = new SortedList<String>();

		try {
			list.get(5);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}

		list.add("apple");
		list.add("banana");

		try {
			list.get(-1);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(2, list.size());
		}

		try {
			list.get(10);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(2, list.size());
		}
	}

	/**
	 * Tests for the add() method
	 */
	@Test
	public void addTest1() {
		SortedList<String> list = new SortedList<String>();
		list.add("apple");
		list.add("pear");
		list.add("banana");
		list.add("cherry");
		list.add("avocado");
		assertThrows(NullPointerException.class, () -> list.add(null));
		assertThrows(IllegalArgumentException.class, () -> list.add("apple"));
	}
}
