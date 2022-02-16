package test;

import lists.LinkedList;

public class AnotherTest {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        System.out.println(list.toString());
    }
}
