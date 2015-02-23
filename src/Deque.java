import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A double-ended queue or deque (pronounced "deck") is a generalization of a
 * stack and a queue that supports
 * adding and removing items from either the front or the back of the data
 * structure
 *
 * Corner cases. Throw a java.lang.NullPointerException if the client
 * attempts  to add a null item; throw a
 * java.util.NoSuchElementException if the client attempts to remove an item
 * from an empty deque; throw a
 * java.lang.UnsupportedOperationException if the client calls the remove()
 * method in the iterator; throw a
 * java.util.NoSuchElementException if the client calls the next() method in
 * the iterator and there are no
 * more items to return.
 *
 * Performance requirements.   Your deque implementation must support each
 * deque operation in constant
 * worst-case time and use space proportional to the number of items
 * currently  in the deque.
 * Additionally, your iterator implementation must support each operation
 * (including construction)
 * in constant worst-case time.
 *
 * Non-iterator operations:             Constant worst-case time
 * Iterator constructor:                Constant worst-case time
 * Other iterator operations:           Constant worst-case time
 * Non-iterator memory use:             Linear in current # of items
 * Memory per iterator:                 Constant
 *
 * A resizing array does not support constant worst-case time operations in a
 * stack. Should use LinkedList.

 * Created by katundwa on 018, 02/18/15.
 */
public class Deque<Item> implements Iterable<Item> {

    private Node head;
    private Node tail;
    private int count;
    private boolean loggingEnabled = false;

    // construct an empty deque
    public Deque() {
        count = 0;
        head = null;
        tail = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return count == 0;
    }

    // return the number of items on the deque
    public int size() {
        return count;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException();

        Node node = new Node(item);
        node.next = head;
        if (node.next != null) {
            node.next.prev = node;
        }
        head = node;

        if (tail == null) {
            tail = node;
        }

        count++;

        log(String.format("Add first item: %d", item));
    }

    // add the item to the end
    public void addLast(Item item) {
        Node node = new Node(item);
        node.prev = tail;
         if (tail != null) {
             tail.next = node;
         }
        tail = node;

        if (head == null) {
            head = node;
        }

        count++;

        log(String.format("Add last item: %d", item));
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();

        Item removedItem = head.item;
        Node removedNode = head;

        if (head.next != null) {
            head.next.prev = null;
        }
        head = head.next;

        //removedNode.item = null;   //loitering
        //removedNode = null;

        count--;    // decrement count;

        log(String.format("Removed first item: %d", removedItem));

        return removedItem;
    }

    // remove and return the item from the end
    public Item removeLast() {
        Item removedItem = tail.item;
        Node removedNode = tail;

        tail = removedNode.prev;

        //removedNode.item = null;
        //removedNode = null;

        count--;

        log(String.format("Removed last item: %d", removedItem));

        return removedItem;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Node curNode  = head;

        @Override
        public boolean hasNext() {
            return curNode != null;
        }

        @Override
        public Item next() {
            Node nodeOfItemToReturn = curNode;
            curNode = curNode.next;
            return nodeOfItemToReturn.item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /*
    private void resizeIfNeeded() {
        if (count == 0) return;

        int newLength = 0;

        if (count == items.length/4) {
            newLength = items.length/2; // resize array in half
        }
        else if ( count == items.length) {
            newLength = items.length * 2; // double the array
        }

        if (newLength > 0) {
            Item[] copy = (Item[]) new Object[newLength];

            for (int counter = 0; counter < count; counter++) {
                copy[counter++] = items[counter];
                items[counter] = null;
            }

            log(String.format("Resized array from %d to %d", items.length,
                    copy.length));

            items = copy;
        }
    }
    */

    private class Node {
        private Item item;
        private Node prev;
        private Node next;

        public Node(Item item) {
            this.item = item;
            this.prev = null;
            this.next = null;
        }

        @Override
        public String toString() {
            return item.toString();
        }
    }

    private void log(String message) {
        if (loggingEnabled) {
            System.out.println(message);
        }
    }

    // unit testing
    public static void main(String[] args) {
        throw new UnsupportedOperationException();
    }
}
