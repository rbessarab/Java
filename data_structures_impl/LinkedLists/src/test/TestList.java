package test;

import lists.LinkedList;

public class TestList {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        //add a few elements
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        //make sure tail pointer still works
        list.remove(5);
        list.add(6);
        System.out.println(list);

        list.remove(6);
        list.remove(4);
        list.add(7);
        System.out.println(list);

        System.out.println("Adding elements");
        for (int i = 10; i < 10000000; i++) {
            list.add(i);
        }

        System.out.println("Removing elements");
        for (int i = 10000000; i < 10; i--) {
            list.remove(i);
        }
    }

    private static void testList() {
        LinkedList list = new LinkedList();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        list.add("g");
        list.add("h");

        System.out.println(list.contains("c"));
        System.out.println(list.contains("f"));
        System.out.println(list.size());

        //access elements by 0
        System.out.println(list.get(0));
        System.out.println(list.get(2));
        System.out.println(list.get(list.size() - 1));
        System.out.println();

        //try to remove some elements
        System.out.println(list.remove("i"));
        System.out.println(list.remove("a"));
        System.out.println(list.remove("d"));
        System.out.println(list.remove("h"));
        System.out.println();

        System.out.println(list.toString());

        System.out.println("Adding");
        for (int i = 1; i < 10000000; i++) {
            list.add(i);
        }
        System.out.println("Done " + list.size());
    }
}
