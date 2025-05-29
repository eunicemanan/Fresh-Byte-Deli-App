package com.pluralsight.model.menu;

import java.util.ArrayList;
import java.util.Arrays;

public class Cheese extends MenuItem {
    // List of valid cheese types
    private static final ArrayList<String> cheeseTypes = new ArrayList<>(
            Arrays.asList("American", "Provolone", "Cheddar", "Swiss"));

    // Valid sizes and their prices
    private static final ArrayList<Integer> sizes = new ArrayList<>(Arrays.asList(4, 8, 12));
    private static final ArrayList<Double> prices = new ArrayList<>(Arrays.asList(0.75, 1.50, 2.25));

    private final int size;
    private final String cheeseType;

    // Constructor
    public Cheese(String cheeseType, int size) {
        super(cheeseType + " Cheese", 0); // Corrected super constructor

        if (!cheeseTypes.contains(cheeseType)) {
            throw new IllegalArgumentException("Invalid cheese type: " + cheeseType);
        }
        this.cheeseType = cheeseType;

        if (!sizes.contains(size)) {
            throw new IllegalArgumentException("Invalid size: " + size);
        }
        this.size = size;
        this.price = calculatePrice(); // Corrected price assignment
    }

    // Calculate price based on size
    private double calculatePrice() {
        int index = sizes.indexOf(size);
        return prices.get(index);
    }

    public int getSize() {
        return size;
    }

    public String getCheeseType() {
        return cheeseType;
    }

    public static ArrayList<String> getCheeseTypes() {
        return cheeseTypes;
    }

    @Override
    public String toString() {
        return cheeseType + " Cheese (" + size + "\") - $" + String.format("%.2f", price);
    }
}
