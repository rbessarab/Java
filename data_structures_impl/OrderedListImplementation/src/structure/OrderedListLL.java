package structure;

import adts.IOrderedList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Represents Ordered List structure based on linked list nodes
 * Adds elements in ascending order
 *
 * @param <T>
 * @author Ruslan Bessarab
 * @date 01.21.2021
 * @version 2.0 iterator added
 */
public class OrderedListLL<T extends Comparable<T>> implements IOrderedList<T> {
    private Node head;
    private int numberOfElements;
    private int modCount;

    /**
     * Searches for and removes the first occurrence of an element in the list.
     * @param element the element to search for
     * @throws java.util.NoSuchElementException if the element is not found
     */
    @Override
    public void remove(T element) {
        //if list doesn't contain the element
        //even if the list is empty it will still throw the exception (should it be like that?)
        if(!contains(element)) {
            throw new NoSuchElementException("Element not found");
        }

        //if it's a first element
        else if(head.data.compareTo(element) == 0) {
            head = head.next;
            numberOfElements--;
            modCount++;
        }

        //if element is somewhere in the list
        else {
            Node prev = head;
            Node current = head.next;
            while(current.data.compareTo(element) != 0) {
                prev = current;
                current = current.next;
            }
            prev.next = current.next;
            numberOfElements--;
            modCount++;
        }
    }

    /**
     * Returns true if the element is located in the collection, or otherwise false.
     * @param element the element to search for
     * @return true of false
     */
    @Override
    public boolean contains(T element) {
        Node current = head;
        while(current != null) {
            if(current.data.compareTo(element) == 0) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Returns the number of elements in the collection.
     * @return size of the collection
     */
    @Override
    public int size() {
        return numberOfElements;
    }

    /**
     * Returns true if the collection is empty, or otherwise false
     * @return true or false based on condition
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Removes all elements from the collection.
     */
    @Override
    public void clear() {
        head = null;
        numberOfElements = 0;
        modCount++;
    }

    /**
     * Adds an element to the collection, while maintaining the order of all
     * elements in the collection. Elements are always in ascending order.
     * @param element the element to add
     */
    @Override
    public void add(T element) {
        //if list is empty
        if(head == null) {
            head = new Node(element);
        }

        //if element should be inserted to the beginning
        else if(element.compareTo(head.data) <= 0) {
            Node newNode = new Node(element);
            newNode.next = head;
            head = newNode;
        }

        //if element should be inserted in middle or end
        else {
            Node current = head;
            while(current.next != null && current.next.data.compareTo(element) <= 0) {
                current = current.next;
            }
            Node newNode = new Node(element);
            newNode.next = current.next;
            current.next = newNode;
        }

        numberOfElements++;
        modCount++;
    }

    /**
     * Returns an element at the specified index.
     * @param index an index in the list
     * @return an element in the list at the given index
     * @throws IndexOutOfBoundsException if given a negative index or an index greater or equal to size()
     */
    @Override
    public T get(int index) {
        //if index is greater than number of elements or given negative index
        if(index >= size() || index < 0) {
            throw new IndexOutOfBoundsException("Given negative index or greater than the actual size");
        }

        else {
            Node current = head;
            int counter = 0;
            while(counter != index) {
                current = current.next;
                counter++;
            }
            return current.data;
        }
    }

    /**
     * String representation of the list
     * @return string representation
     */
    @Override
    public String toString() {
        Node current = head;
        StringBuilder result = new StringBuilder("head -> ");
        while(current != null) {
            result.append(current.data.toString()).append(" -> ");
            current = current.next;
        }
        return result + "null";
    }

    /**
     * Iterates through each element in the data structure so we can use for each loop
     * @return new iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new ListIterator(modCount);
    }

    private class ListIterator implements Iterator<T> {
        //current node
        private Node current;
        private int saveModCount;

        //assigning current node to the head to keep track on each element
        public ListIterator(int modCount) {
            current = head;
            saveModCount = modCount;
        }

        //check if current not equals null (if list is empty)
        @Override
        public boolean hasNext() {
            checkChange();
            return current != null;
        }

        //going to next node
        @Override
        public T next() {
            checkChange();
            T item = current.data;
            current = current.next;
            return item;
        }

        //check if client tries to change the structure while iterating
        public void checkChange() {
            if(modCount != saveModCount) {
                throw new ConcurrentModificationException("You cannot change the structure while iterating");
            }
        }
    }

    private class Node {
        private T data;
        private Node next;

        public Node(T data) {
            this.data = data;
        }

        public String toString() {
            return data.toString();
        }
    }
}
