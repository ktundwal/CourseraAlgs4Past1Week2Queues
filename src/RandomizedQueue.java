import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A randomized queue is similar to a stack or queue, except that the item
 * removed is chosen uniformly at
 * random from items in the data structure
 *
 * Corner cases. The order of two or more iterators to the same randomized
 * queue must be mutually independent;
 * each iterator must maintain its own random order. Throw a  java.lang
 * .NullPointerException
 * if the client attempts to add a null item; throw a  java.util
 * .NoSuchElementException if
 * the client attempts to sample or dequeue an item from an empty  randomized
 * queue; throw
 * a java.lang.UnsupportedOperationException if the client calls the remove()
 * method in the
 * iterator; throw a java.util.NoSuchElementException if the client calls the
 * next() method
 * in the iterator and there are no more items to return.
 *
 * Performance requirements. Your randomized queue implementation must
 * support each
 * randomized queue operation (besides creating an iterator) in constant
 * amortized time
 * and use space proportional to the number of items currently in the queue.
 * That is, any
 * sequence of M randomized queue operations (starting from an empty queue)
 * should take at
 * most cM steps in the worst case, for some constant c. Additionally, your
 * iterator implementation
 * must support operations next() and hasNext() in constant worst-case time;
 * and construction in
 * linear time; you may use a linear amount of extra memory per iterator.
 *
 * Non-iterator operations:             Constant amortized time
 * Iterator constructor:                linear in current # of items
 * Other iterator operations:           Constant worst-case time
 * Non-iterator memory use:             Linear in current # of items
 * Memory per iterator:                 Linear in current # of items
 *
 * What is meant by uniformly at random? If there are N items in the
 * randomized queue,
 * then you should choose each one with probability 1/N, up to the randomness
 * of StdRandom.uniform(),
 * independent of past decisions. You can generate a pseudo-random integer
 * between 0 and N-1 using
 * StdRandom.uniform(N) from the StdRandom.java library.
 *
 * Created by katundwa on 018, 02/18/15.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Node head;
    private Node tail;
    private int count;
    private final boolean loggingEnabled = false;

    // construct an empty randomized queue
    public RandomizedQueue() {
        count = 0;
        head = null;
        tail = null;
    }

    // is the queue empty?
    public boolean isEmpty() {
        return count == 0;
    }

    // return the number of items on the queue
    public int size() {
        return count;
    }

    // add the item
    public void enqueue(Item item) {
        Node node = new Node(item);
        if (head == null) {
            head = node;
            tail = node;
        }
        else {
            tail.next = node;
            tail = node;
        }
        count++;
        //log(String.format("Queued %d", item.toString()));
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();

        Node prev = null;
        Node cur = head;

        int randomIndex = StdRandom.uniform(count);
        for (int index = 1; index < randomIndex; index++) {
            prev = cur;
            cur = cur.next;
        }

        Item dequeuedItem = cur.item;

        // remove cur and set it to null
        if (prev == null) {
            // we are removing first node. move the head
            head = cur.next;
        }
        else {
            prev.next = cur.next;
        }
        cur.item = null;
        cur = null;

        // reduce count
        count--;

        //log(String.format("Dequeued %d from position %d", dequeuedItem,
        //        randomIndex));

        return dequeuedItem;
    }

    // return (but do not remove) a random item
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();

        int randomIndex = StdRandom.uniform(count);

        Node randomNode = head;
        for (int index = 0; index < randomIndex; index++) {
            randomNode = randomNode.next;
        }

        Item randomItem = randomNode.item;

        log(String.format("Returning random item %d from position %d",
                randomItem,
                randomIndex));

        return randomNode.item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {

        private Item[] nodes = null;
        private int itemsReturned = 0;

        public QueueIterator() {
            nodes = (Item[]) new Object[count];
            int counter = 0;
            Node cur = head;
            while (cur != null) {
                nodes[counter++] = cur.item;
                cur = cur.next;
            }
            StdRandom.shuffle(nodes);
        }

        @Override
        public boolean hasNext() {
            return itemsReturned < nodes.length;
        }

        @Override
        public Item next() {
            return nodes[itemsReturned++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private void log(String message) {
        if (loggingEnabled) {
            System.out.println(message);
        }
    }

    private class Node {
        private Item item;
        private Node next;

        public Node(Item item) {
            this.item = item;
            this.next = null;
        }

        @Override
        public String toString() {
            return item.toString();
        }
    }

    // unit testing
    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
        int intToDeque = 3;
        q.enqueue(intToDeque);
        int dequeudInt = (int) q.dequeue();
        StdOut.println(String.format("Queued and dequed items are same: %b",
                intToDeque == dequeudInt));
    }
}
