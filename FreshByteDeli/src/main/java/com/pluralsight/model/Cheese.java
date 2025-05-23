package com.pluralsight.model;

public class Cheese extends MenuItem {
    // Constants for cheese types to avoid typos and keep code consistent
    public static final String AMERICAN = "American";
    public static final String PROVOLONE = "Provolone";
    public static final String CHEDDAR = "Cheddar";
    public static final String SWISS = "Swiss";

    // Size of the sandwich in inches (4, 8, or 12)
    private int size;

    public Cheese(String name, int size) {
        // Call the parent constructor with name and temporary price 0
        super(name, 0);
        this.size = size;

        // Calculate the price based on the size and assign it
        this.price = calculatePrice(size);
    }

    public int getSize() {
        return size;
    }

    private double calculatePrice(int size) {
        if (size == 4) {
            return 0.75;
        } else if (size == 8) {
            return 1.50;
        } else if (size == 12) {
            return 2.25;
        } else {
            return 0;
        }
    }

 /*   toString method returns a readable description of this cheese item,
     including the name, size, and price formatted nicely.*/

    @Override
    public String toString() {
        return getName() + " Cheese (" + size + "\") - $" + String.format("%.2f", getPrice());
    }
}

