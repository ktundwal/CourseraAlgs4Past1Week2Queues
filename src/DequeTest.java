import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.Assert.*;

public class DequeTest {

    @Test
    public void testAddFirstForSingleItem() throws Exception {
        Deque<Integer> q = new Deque<Integer>();
        q.addFirst(3);
        assertEquals("Expected element wasnt dequeued", (int) q.removeFirst(), 3);
        assertTrue("Queue is not empty", q.isEmpty());
    }

    @Test
    public void testAddLastForSingleItem() throws Exception {
        Deque<Integer> q = new Deque<Integer>();
        q.addLast(3);
        assertEquals("Expected element wasnt dequeued", (int) q.removeLast(),
                3);
        assertTrue("Queue is not empty", q.isEmpty());
    }

    @Test
     public void testAddRemoveFor3Ints() throws Exception {
        testArray(new int[] {23,45,-3});
    }

    @Test
    public void testAddRemoveFor1KInts() throws Exception {
        In stream = new In("http://algs4.cs.princeton.edu/14analysis/1Kints.txt");
        testArray(stream.readAllInts());
    }

    private void testArray(int[] ints) {
        Deque<Integer> q = new Deque<Integer>();
        int numAddedItems = 0;

        for (int i : ints) {
            if (StdRandom.bernoulli()) {
                q.addFirst(i);
            } else {
                q.addLast(i);
            }
            numAddedItems++;
        }

        int numRemovedItems = 0;
        int[] removedInts = new int[numAddedItems];
        while (!q.isEmpty()) {
            removedInts[numRemovedItems++] = StdRandom.bernoulli() ? q
                    .removeFirst() : q.removeLast();
        }

        assertEquals(numAddedItems, numRemovedItems);

        Arrays.sort(ints);
        Arrays.sort(removedInts);
        assertArrayEquals("Queued and dequed ints are not same", ints,
                removedInts);
    }

    @Test
    public void testIteratorFor1KInts() throws Exception {
        In stream = new In("http://algs4.cs.princeton.edu/14analysis/1Kints.txt");
        int[] ints = stream.readAllInts();

        ints = new int[] {23,45,-3};

        Deque<Integer> q = new Deque<Integer>();
        int numAddedItems = 0;

        for (int i : ints) {
            if (StdRandom.bernoulli()) {
                q.addFirst(i);
            } else {
                q.addLast(i);
            }
            numAddedItems++;
        }

        int iteratedItems = 0;
        int[] iteratedInts = new int[numAddedItems];

        Iterator<Integer> iterator = q.iterator();
        while (iterator.hasNext()) {
            iteratedInts[iteratedItems++] = iterator.next();
        }
        assertEquals(numAddedItems, iteratedItems);

        Arrays.sort(ints);
        Arrays.sort(iteratedInts);

        assertArrayEquals("Queued and iterated ints are not same", ints,
                iteratedInts);
    }
}