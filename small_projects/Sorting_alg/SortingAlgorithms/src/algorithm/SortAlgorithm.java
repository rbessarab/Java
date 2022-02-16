package algorithm;

import java.util.Arrays;

/**
 * @author Ruslan Bessarab
 * @version 1.0
 *
 * This class represents sorting algorithm with a small visualization.
 * Sort is based on comparing each element against groups.
 */
public class SortAlgorithm {
    private int low;
    private int high;
    private double groupSize;
    private int[] arr;
    private int[] thresholds;
    private Node[] sorting;

    /**
     * @param arr array to be sorted
     * @param groups groups required by user
     */
    public void sort(int[] arr, int groups) {
        this.arr = arr;
        sorting = new Node[groups];
        low = lowest(arr); //n
        high = high(arr); //n
        groupSize = groupSize(low, high, groups); //1
        thresholds = thresholds(low, high, groupSize, groups);

        //sorting
        //for each element in the array we compare it against each group
        for (int value : arr) {
            for (int j = 0; j < thresholds.length; j++) {
                //not sure if it's good approach
                if (value <= thresholds[j]) {
                    //insertion sort
                    //if list is empty
                    if(sorting[j] == null) {
                        sorting[j] = new Node(value);
                    }
                    //if element should be inserted to the beginning
                    else if(value <= sorting[j].data) {
                        Node newNode = new Node(value);
                        newNode.next = sorting[j];
                        sorting[j] = newNode;
                    }
                    //if element should be inserted in middle or end
                    else {
                        Node current = sorting[j];
                        while (current.next != null && value > current.next.data) {
                            current = current.next;
                        }
                        Node newNode = new Node(value);
                        newNode.next = current.next;
                        current.next = newNode;
                    }
                    break;
                }
            }
        }

        //finishing the sort
        int counter = 0;
        for(Node n : sorting) {
            while(n != null) {
                arr[counter] = n.data;
                n = n.next;
                counter++;
            }
        }
    }

    /**
     * Displaying the last part of visualization with sorted array and inversions detected
     */
    public void displaySorted() {
        System.out.println("Sorted: " + Arrays.toString(arr));
        System.out.println("Detected inversions? " + inversions(arr));
    }

    /**
     * Displays array of nodes and range of each group
     */
    public void displayingNodeArray() {
        for(int i = 0; i < sorting.length; i++) {
            if(i == 0) {
                System.out.print(i + "[" + low + "-" + thresholds[i] + "]: ");
            }
            else if(i == sorting.length - 1) {
                System.out.print(i + "[" + (thresholds[i - 1] + 1) + "-" + high + "]: ");
            }
            else {
                System.out.print(i + "[" + (thresholds[i - 1] + 1) + "-"
                + thresholds[i] + "]: ");
            }

            if(sorting[i] == null) {
                System.out.print("null");
            }
            while(sorting[i] != null) {
                if(sorting[i].next == null) {
                    System.out.print(sorting[i].data);
                }
                else {
                    System.out.print(sorting[i].data + " -> ");
                }
                sorting[i] = sorting[i].next;
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * displays original array
     * @param arr array
     */
    public void displayingOriginalArray(int[] arr) {
        System.out.println("Generating an array of size " + arr.length + " with elements");
        System.out.println("Original: " + Arrays.toString(arr));
        System.out.println();
    }

    /**
     * displays the starting information of low and high values, array etc.
     * @param groups number of groups required
     */
    public void displayingStarterInfo(int groups) {
        System.out.println("Sorting with " + groups + " groups");
        System.out.println("Min/max: " + "[" + low + ", " + high + "]");
        System.out.println("Group size: " + groupSize);
        System.out.println("Group thresholds: " + Arrays.toString(thresholds));
        System.out.println();
    }

    private boolean inversions(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if(arr[i] > arr[i + 1]) {
                return true;
            }
        }
        return false;
    }

    private int[] thresholds(int low, int high, double groupSize, int groups) {
        int[] thresholds = new int[groups];
        int helper = 1;
        for (int i = 0; i < groups - 1; i++) {
            if(helper == 1) {
                thresholds[i] = (int) (low + Math.floor(groupSize));
                helper = 0;
                low += Math.floor(groupSize);
            }
            else {
                thresholds[i] = (int) (low + Math.ceil(groupSize));
                helper = 1;
                low += Math.ceil(groupSize);
            }
        }
        thresholds[groups - 1] = high;
        return thresholds;
    }

    private double groupSize(int low, int high, int groups) {
        int range =  high - low + 1;
        return (double) range / groups;
    }

    private int lowest(int[] arr) {
        int low = arr[0];
        for (int j : arr) {
            if (low > j) {
                low = j;
            }
        }
        return low;
    }

    private int high(int[] arr) {
        int high = arr[0];
        for (int j : arr) {
            if (high < j) {
                high = j;
            }
        }
        return high;
    }

    private static class Node {
        private int data;
        private Node next;

        public Node(int data) {
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

    @Override
    public String toString() {
        return "SortAlgorithm{" +
                "low=" + low +
                ", high=" + high +
                ", groupSize=" + groupSize +
                ", arr=" + Arrays.toString(arr) +
                ", thresholds=" + Arrays.toString(thresholds) +
                ", sorting=" + Arrays.toString(sorting) +
                '}';
    }
}
