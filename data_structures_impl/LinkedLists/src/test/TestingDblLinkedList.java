package test;

import lists.DoubleLinkedList;

public class TestingDblLinkedList {
    public static void main(String[] args) {
        DoubleLinkedList list = new DoubleLinkedList();
        list.add(5);
        list.add(6);
        list.add(3);
        list.add(4);
        list.add(7);
        list.add(10);

        System.out.println(list.toString());
    }
}
