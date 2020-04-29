/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Item[] itemArray;

    public RandomizedQueue() {
        size = 0;
        itemArray = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Please input valid item!");
        }

        if (size == itemArray.length) {
            resizeArray(itemArray.length * 2);
        }

        itemArray[size] = item;
        size++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty!");
        }

        int randomIndex = StdRandom.uniform(size);
        Item item = itemArray[randomIndex];

        itemArray[randomIndex] = itemArray[size - 1];
        itemArray[size - 1] = null;
        size--;

        if (size > 0 && (size == itemArray.length / 4)) {
            resizeArray(itemArray.length / 2);
        }

        return item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty!");
        }

        int randomIndex = StdRandom.uniform(size);

        return itemArray[randomIndex];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    public static void main(String[] args) {
        
    }

    /*
    PRIVATE CLASSES AND FUCNTIONS
     */

    private class RandomizedQueueIterator implements Iterator<Item> {

        private int randomIndexRange;
        private Item[] itemsCopy;

        public RandomizedQueueIterator() {
            randomIndexRange = size;
            itemsCopy = (Item[]) new Object[randomIndexRange];
            for (int i = 0; i < randomIndexRange; i++) {
                itemsCopy[i] = itemArray[i];
            }
        }

        public boolean hasNext() {
            return randomIndexRange > 0;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Queue is empty!");
            }
            int randomIndex = StdRandom.uniform(randomIndexRange);
            Item item = itemsCopy[randomIndex];

            itemsCopy[randomIndex] = itemsCopy[randomIndexRange - 1];
            itemsCopy[randomIndexRange - 1] = null;
            randomIndexRange--;
            return item;
        }

        /*
        DEPRECIATED METHODS
         */

        public void remove() {
            throw new UnsupportedOperationException("Depreciated function call!");
        }
    }

    private void resizeArray(int newSize) {
        Item[] newItems = (Item[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            newItems[i] = itemArray[i];
        }

        itemArray = newItems;
    }
}


