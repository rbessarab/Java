package tests;

import structures.Map;

public class test {
    public static void main(String[] args) {
        Map<String, Integer> map = new Map<>();

        map.add("Ruslan", 22);
        map.add("Zina", 24);
        map.add("Krest", 18);

        map.add("Ruslan", 50);
        System.out.println(map.get("Ruslan"));
    }
}
