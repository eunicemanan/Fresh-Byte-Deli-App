package com.pluralsight.model.menu;

import java.util.ArrayList;
import java.util.Arrays;

public class Meat extends MenuItem {

    // List of valid meat types
    private static final ArrayList<String> meatTypes = new ArrayList<>(Arrays.asList(
            "Steak", "Ham", "Salami", "Roast Beef", "Chicken", "Bacon"
    ));

    // Sizes and their corresponding prices
    private static final ArrayList<Integer> sizes = new ArrayList<>(Arrays.asList(4, 8, 12));
    private static final ArrayList<Double> basePrices = new ArrayList<>(Arrays.asList(1.00, 2.00, 3.00));
    private static final ArrayList<Double> extraPrices = new ArrayList<>(Arrays.asList(0.50, 1.00, 1.50));

    // Instance fields
    private int size;
    private int extraMeatCount;

    // Constructor
    public Meat(String name, int size, int extraMeatCount) {
        super(name, 0); // price will be calculated

        // Validate meat type
        if (!meatTypes.contains(name)) {
            throw new IllegalArgumentException("Invalid meat type: " + name);
        }

        // Validate size
        if (!sizes.contains(size)) {
            throw new IllegalArgumentException("Invalid size: " + size);
        }

        this.size = size;
        this.extraMeatCount = extraMeatCount;
        this.price = calculatePrice(size, extraMeatCount);
    }

    // Price calculation logic
    private double calculatePrice(int size, int extraMeatCount) {
        int index = sizes.indexOf(size);
        double basePrice = basePrices.get(index);
        double extraPrice = extraPrices.get(index);
        return basePrice + (extraPrice * extraMeatCount);
    }

    // Getters
    public int getSize() {
        return size;
    }

    public int getExtraMeatCount() {
        return extraMeatCount;
    }

    public static ArrayList<String> getMeatTypes() {
        return meatTypes;
    }

    @Override
    public String toString() {
        return getName() + " (" + size + "\")" +
                (extraMeatCount > 0 ? " with " + extraMeatCount + "x extra meat" : "") +
                " - $" + String.format("%.2f", getPrice());
    }
}
