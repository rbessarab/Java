package structures;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Ruslan Bessarab
 * @version 1.0
 *
 * This class represents a hash table with separate chaining
 * @param <T> generic Hash Table
 */
public class HashTable<T> implements ICollection<T> {
    private static final double MAX_LOADING_FACTOR = 2.5;
    private static final int DEFAULT_SIZE = 10;
    private int size;
    private Node<T>[] table;
    private int modCount;

    /**
     * Default constructor with default size
     */
    public HashTable() {
        table = new Node[DEFAULT_SIZE];
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            table[i] = new Node<>();
        }
    }

    /**
     * Adds an element to the collection. No specific ordering
     * is required.
     *
     * @throws IllegalArgumentException when adding duplicates
     * @param element the new element to put in the collection
     */
    @Override
    public void add(T element) {
        //if element is already in the table - throw an exception
        if(contains(element)) {
            throw new IllegalArgumentException("Duplicates are not allowed.");
        }

        //otherwise - add an element to the table
        else {
            //resize if load factor was reached
            double loadFactor = (double) size / table.length;
            if(loadFactor >= MAX_LOADING_FACTOR) {
                resize();
            }

            //find the place where the element should be
            int hashCode = Math.abs(element.hashCode());
            int index = hashCode % table.length;

            //go through the list until empty spot found
            Node<T> current = table[index];
            while(current.next != null) {
                current = current.next;
            }

            current.next = new Node<>(element);
            size++;
            modCount++;
        }
    }

    private void resize() {
        //creating a new table with 50% more space
        Node<T>[] oldTable = table;
        table = new Node[oldTable.length * 2];

        //assigning size to zero
        size = 0;
        modCount++;

        //creating new node for each element in the table
        for (int i = 0; i < table.length; i++) {
            table[i] = new Node<>();
        }

        //loop over old table and put into a new table
        for (Node<T> tNode : oldTable) {
            Node<T> current = tNode;
            while (current.next != null) {
                //put each element into a new table
                add(current.next.data);
                current = current.next;
            }
        }
    }

    /**
     * Finds and removes an element from the collection.
     *
     * @throws java.util.NoSuchElementException thrown when the
     * element is not found in the collection
     * @param element the element to remove
     */
    @Override
    public void remove(T element) {
        //check whether element is in the table
        if(!contains(element)) {
            throw new NoSuchElementException("Element is not found.");
        }

        //otherwise - remove the element
        else {
            //hash code to see where the element suppose to be
            int hashCode = Math.abs(element.hashCode());
            int index = hashCode % table.length;

            //remove the element
            Node<T> current = table[index];
            while(current.next != null) {
                //element found
                if(current.next.data.equals(element)) {
                    //if first element needs to be removed
                    if(current.next.next == null) {
                        current.next = null;
                    }
                    else {
                        current.next = current.next.next;
                    }
                    size--;
                    modCount++;
                    break;
                }
                current = current.next;
            }
        }
    }

    /**
     * Reports whether the collection contains an element.
     *
     * @param element the element to search for.
     * @return true if the element is found, otherwise false
     */
    @Override
    public boolean contains(T element) {
        //hash code to see where the element suppose to be
        int hashCode = Math.abs(element.hashCode());
        int index = hashCode % table.length;

        Node<T> current = table[index];
        while(current.next != null) {
            if(current.next.data.equals(element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Returns the number of elements in the collection.
     *
     * @return the number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Reports whether the collection is empty or not.
     *
     * @return true if the collection is empty, otherwise false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes all elements from the collection.
     */
    @Override
    public void clear() {
        for (int i = 0; i < table.length; i++) {
            table[i] = new Node<>();
        }
        size = 0;
        modCount++;
    }

    @Override
    public String toString() {
        return "HashTable{" +
                "table=" + Arrays.toString(table) +
                '}';
    }

    /**
     * Returns an element in the collection that matches the
     * input parameter according the equals() method of the
     * parameter.
     *
     * @param element an element to search for
     * @return a matching element
     */
    @Override
    public T get(T element) {
        Node<T> position = position(element);
        if(position != null) {
            return position.data;
        }
        else {
            return null;
        }
    }

    private Node<T> position(T element) {
        //find the place where the element should be
        int hashCode = Math.abs(element.hashCode());
        int index = hashCode % table.length;

        Node<T> current = table[index];
        while(current.next != null) {
            if(current.next.data.equals(element)) {
                return current.next;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Returns an iterator over the collection.
     *
     * @return an object using the Iterator<T> interface
     */
    @Override
    public Iterator<T> iterator() {
        return new HashTableIterator(modCount);
    }

    //inner classes
    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node() {
        }

        public Node(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", next=" + next +
                    '}';
        }
    }

    private class HashTableIterator implements Iterator<T> {
        private int currentIndex;
        private int elementsLeft;
        private Node<T> currentListElement;
        private Node<T>[] iteratorTable;
        private int saveModCount;

        //default constructor
        public HashTableIterator(int modCount) {
            saveModCount = modCount;
            currentIndex = 0;
            iteratorTable = table;
            elementsLeft = size;
            if(size != 0) {
                currentListElement = findNotEmptyList();
            }
        }

        @Override
        public boolean hasNext() {
            checkChange();
            return elementsLeft > 0;
        }

        @Override
        public T next() {
            checkChange();
            //if only one element in a chain
            if(currentListElement.next == null) {
                elementsLeft--;
                T element = currentListElement.data;
                if(elementsLeft != 0) {
                    currentListElement = findNotEmptyList();
                }
                return element;
            }
            else {
                Node<T> element = currentListElement;
                currentListElement = currentListElement.next;
                elementsLeft--;
                return element.data;
            }
        }

        private Node<T> findNotEmptyList() {
            while(iteratorTable[currentIndex].next == null) {
                currentIndex++;
            }

            Node<T> notEmptyChain = iteratorTable[currentIndex].next;
            currentIndex++;
            return notEmptyChain;
        }

        private void checkChange() {
            if(modCount != saveModCount) {
                throw new ConcurrentModificationException("You cannot change the structure while iterating");
            }
        }

        @Override
        public String toString() {
            return "HashTableIterator{" +
                    "currentIndex=" + currentIndex +
                    ", elementsLeft=" + elementsLeft +
                    ", currentListElement=" + currentListElement +
                    ", iteratorTable=" + Arrays.toString(iteratorTable) +
                    '}';
        }
    }
}