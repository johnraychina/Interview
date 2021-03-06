package google;

import java.util.Iterator;
import java.util.LinkedList;

/*
 * Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that support the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().
 * Here is an example. Assume that the iterator is initialized to the beginning of the list: [1, 2, 3].
 * Call next() gets you 1, the first element in the list.
 * Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.
 * You call next() the final time and it returns 3, the last element. Calling hasNext() after that should return false.
 * Hint:
 * Think of "looking ahead". You want to cache the next element.
 * Is one variable sufficient? Why or why not?
 * Test your design with call order of peek() before next() vs next() before peek().
 * For a clean implementation, check out Google's guava library source code.
 * Follow up: How would you extend your design to be generic and work with all types, not just integer?
 */
public class PeekingIterator {
	public Iterator<Integer> it;
	public int cache;
	public boolean isPeek;

	public PeekingIterator(Iterator<Integer> iterator) {
		// initialize any member here.
		it = iterator;
		isPeek = false;
	}

	// Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
		if (isPeek)
			return cache;
		cache = it.next();
		isPeek = true;
		return cache;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	public Integer next() {
		if (isPeek) {
			isPeek = false;
			return cache;
		}
		return it.next();
	}

	public boolean hasNext() {
		if (isPeek)
			return true;
		return it.hasNext();
	}

	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<>();
		list.add(10);
		list.add(20);
		list.add(30);
		list.add(40);
		PeekingIterator it = new PeekingIterator(list.iterator());
		
		System.out.println(it.hasNext());
		System.out.println(it.next());
		System.out.println(it.peek());
		System.out.println(it.peek());
		System.out.println(it.next());
		System.out.println(it.hasNext());
	}
}
