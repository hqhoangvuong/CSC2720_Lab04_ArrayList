package com.example.csc2720;

public class App {
    public static void main(final String[] args) {
        ArrayList<Integer> a = new ArrayList<Integer>();

        a.add(5);
        a.add(12);
        a.add(9);
        a.add(7);
        a.add(18);
        a.add(0);
        a.add(21);

        System.out.print("Original array: ");
        for (int i = 1; i <= a.getLength(); i++) {
            System.out.print(a.getEntry(i) + " ");
        }
        System.out.println();

        System.out.println("Removed value: " + a.removeMin());

        System.out.print("Array after removed min value: ");
        for (int i = 1; i <= a.getLength(); i++) {
            System.out.print(a.getEntry(i) + " ");
        }
        System.out.println();

        System.out.println("Current min value: " + a.getMin());

    }
}
