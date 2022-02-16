package informal_test;

import structure.OrderedListLL;

/**
 * Informal testing class for testing each method from OrderedListLL class
 * @author Ruslan Bessarab
 * @version 1.0
 */
public class Testing {
    public static final int BIGNUMBER = 10000;

    /**
     * I don't understand why we need javadoc for the main method
     * but it is the main method for testing purposes
     * @param args some description
     */
    public static void main(String[] args) {
        OrderedListLL<Integer> myList = new OrderedListLL<>();

        //Testing adding 10,000 element and than clear it
        System.out.println("Test 1: \n");

        for (int i = 0; i < BIGNUMBER; i++) {
            myList.add(i);
        }

        System.out.println(myList);
        System.out.println("Is this list empty? " + myList.isEmpty());

        System.out.println("Size: " + myList.size());
        myList.clear();
        System.out.println("Size after clearing the list: " + myList.size());

        //testing adding some elements not in order and remove some of them
        System.out.println();
        System.out.println("Test 2: \n");

        //adding elements
        myList.add(5);
        myList.add(-7);
        myList.add(10);
        myList.add(5);
        myList.add(10);
        myList.add(3);
        myList.add(0);
        myList.add(9);

        System.out.println(myList);
        System.out.println("Size: " + myList.size());
        //check if contains 5
        System.out.println("Is there 5 in list? " + myList.contains(5));

        //remove some elements (first, last and middle)
        myList.remove(-7);
        myList.remove(10);
        myList.remove(3);
        System.out.println("List after removing: " + myList);
        System.out.println("Is there 3 in list? " + myList.contains(3));

        System.out.println();
        System.out.println("Test 3: \n");
        //testing exceptions
        //1. Getting element from list
        System.out.println(myList);
        System.out.println(myList.get(2));

        //throw exceptions
        System.out.println(myList.get(-5));
        System.out.println(myList.get(10));

        //let's try to remove the element which is not in the list
        myList.remove(2);
    }
}
