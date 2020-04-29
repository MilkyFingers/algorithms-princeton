/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first = null;
    private Node last = null;
    private int listSize = 0;

    // This class creates a node that stores poniters in both directions
    private class Node {
        private Item item;
        private Node next;
        private Node previous;

        public void setItem(Item newItem) {
            item = newItem;
        }

        public Item getItem() {
            return item;
        }

        public void setNextNode(Node newNextNode) {
            next = newNextNode;
        }

        public Node getNextNode() {
            return next;
        }

        public void setPreviousNode(Node newPrevious) {
            previous = newPrevious;
        }

        public Node getPreviousNode() {
            return previous;
        }
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public void remove() {
            throw new UnsupportedOperationException("depreciated operation");
        }

        public boolean hasNext() {
            return !(current.getNextNode() == null);
        }

        public Item next() {
            if (current.getNextNode() == null) {
                throw new NoSuchElementException("No next element!");
            }
            Item item = current.getItem();
            current = current.getNextNode();
            return item;
        }

    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return listSize;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Must be an object!");
        }
        Node newFirst = new Node();
        newFirst.setItem(item);
        if (size() == 0) {
            newFirst.setNextNode(null);
        }
        else {
            newFirst.setNextNode(first);
        }
        newFirst.setPreviousNode(null);
        if (size() > 0) {
            first.setPreviousNode(newFirst);
        }
        first = newFirst;
        listSize++;
        if (listSize == 1) {
            last = first;
        }
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Must be an object!");
        }
        Node newLast = new Node();
        newLast.setItem(item);
        if (size() == 0) {
            newLast.setPreviousNode(null);
        }
        else {
            newLast.setPreviousNode(last);
        }
        newLast.setNextNode(null);
        if (size() > 0) {
            last.setNextNode(newLast);
        }
        last = newLast;
        listSize++;
        if (listSize == 1) {
            first = last;
        }
    }

    public Item removeFirst() {
        if (size() == 0) {
            throw new java.util.NoSuchElementException("List is empty");
        }
        Item currentFirstItem = first.getItem();
        if (size() > 1) {
            first = first.getNextNode();
        }
        else {
            first = null;
            last = null;
        }
        --listSize;
        return currentFirstItem;
    }

    public Item removeLast() {
        if (size() == 0) {
            throw new java.util.NoSuchElementException("List is empty");
        }
        Item currentLastItem = last.getItem();
        if (size() > 1) {
            last = last.getPreviousNode();
        }
        else {
            first = null;
            last = null;
        }
        --listSize;
        return currentLastItem;
    }


    public static void main(String[] args) {
    }


    /*
    PRIVATE METHODS
     */
}
