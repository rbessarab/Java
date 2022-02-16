package testing;

import algorithm.SortAlgorithm;

/**
 * Driver class which provides 3 tests - 2 from assignment description and one form me
 * @author Ruslan Bessarab
 * @version 1.0
 */
public class Driver {
    /**
     * main
     * @param args argument
     */
    public static void main(String[] args) {
        SortAlgorithm sorting = new SortAlgorithm();

        firstTest(sorting);
        System.out.println();
        secondTest(sorting);
        System.out.println();

        //my own test
        myTest(sorting);
    }

    /**
     * tests the sorting against big array
     * @param sorting class
     */
    public static void myTest(SortAlgorithm sorting) {
        int size = 1000;
        int groups = 100;
        int[] arr = new int[size];

        //put random numbers into array
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) Math.ceil(Math.random() * size);
        }

        sorting.displayingOriginalArray(arr);
        sorting.sort(arr, groups);
        sorting.displayingStarterInfo(groups);
        sorting.displayingNodeArray();
        sorting.displaySorted();
    }

    /**
     * tests the first array as provided in the example of assignment description
     * @param sorting class
     */
    public static void firstTest(SortAlgorithm sorting) {
        int[] arr = {22, 18, 22, 11, 16, 12, 9, 29, 5, 28, 8, 28, 22, 24, 26};

        //displaying information
        sorting.displayingOriginalArray(arr);
        sorting.sort(arr, 5);
        sorting.displayingStarterInfo(5);
        sorting.displayingNodeArray();
        sorting.displaySorted();
    }

    /**
     * tests the second array as provided in the example of assignment description
     * @param sorting class
     */
    public static void secondTest(SortAlgorithm sorting) {
        int[] arr = {19, 21, 46, 49, 20, 39, 77, 13, 29, 56, 67, 75, 28, 81, 68, 39, 67, 31, 44, 93};

        //displaying information
        sorting.displayingOriginalArray(arr);
        sorting.sort(arr, 15);
        sorting.displayingStarterInfo(15);
        sorting.displayingNodeArray();
        sorting.displaySorted();
    }
}
