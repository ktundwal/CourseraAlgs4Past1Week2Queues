import org.junit.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.Assert.*;

public class RandomizedQueueTest {

    @Test
    public void testEnqueueDequeForSingleItem() throws Exception {
        RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
        q.enqueue(3);
        assertEquals("Expected element wasnt dequeued", (int) q.dequeue(), 3);
        assertTrue("Queue is not empty", q.isEmpty());
    }

    @Test
    public void testEnqueueDequeue2Ints() throws Exception {
        RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();

        int numQueuedItems = 0;
        int[] ints = new int[] {23,45,-3};
        for (int i : ints) {
            q.enqueue(i);
            numQueuedItems++;
        }

        int numDequeuedItems = 0;
        int[] dequeudInts = new int[numQueuedItems];
        while (!q.isEmpty()) {
            dequeudInts[numDequeuedItems++] = q.dequeue();
        }

        assertEquals(numQueuedItems, numDequeuedItems);

        Arrays.sort(ints);
        Arrays.sort(dequeudInts);
        assertArrayEquals("Queued and dequed ints are not same", ints,
                dequeudInts);
    }

    @Test
    public void testEnqueueDequeueFor1KInts() throws Exception {
        RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
        In stream = new In("http://algs4.cs.princeton.edu/14analysis/1Kints.txt");

        int numQueuedItems = 0;
        int[] ints = stream.readAllInts();
        for (int i : ints) {
            q.enqueue(i);
            numQueuedItems++;
        }

        int numDequeuedItems = 0;
        int[] dequeudInts = new int[numQueuedItems];
        while (!q.isEmpty()) {
            dequeudInts[numDequeuedItems++] = q.dequeue();
        }

        assertEquals(numQueuedItems, numDequeuedItems);

        Arrays.sort(ints);
        Arrays.sort(dequeudInts);
        assertArrayEquals("Queued and dequed ints are not same", ints,
                dequeudInts);
    }

    @Test
    public void testIteratorFor1KInts() throws Exception {
        RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
        In stream = new In("http://algs4.cs.princeton.edu/14analysis/1Kints.txt");

        int numQueuedItems = 0;
        int[] ints = stream.readAllInts();
        for (int i : ints) {
            q.enqueue(i);
            numQueuedItems++;
        }

        int iteratedItems = 0;
        int[] iteratedInts = new int[numQueuedItems];

        Iterator<Integer> iterator = q.iterator();
        while (iterator.hasNext()) {
            iteratedInts[iteratedItems++] = iterator.next();
        }
        assertEquals(numQueuedItems, iteratedItems);

        Arrays.sort(ints);
        Arrays.sort(iteratedInts);

        assertArrayEquals("Queued and iterated ints are not same", ints,
                iteratedInts);

    }
}