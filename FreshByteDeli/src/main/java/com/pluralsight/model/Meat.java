package com.pluralsight.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Meat extends MenuItem {
    // Define sizes and prices with inline initialization
    private static final ArrayList<Integer> sizes = new ArrayList<Integer>(Arrays.asList(4, 8, 12));

    private static final ArrayList<Double> basePrices = new ArrayList<Double>(Arrays.asList(1.00, 2.00, 3.00));

    private static final ArrayList<Double> extraPrices = new ArrayList<Double>(Arrays.asList(0.50, 1.00, 1.50));
//  unique fields
    private int size;
    private int extraMeatCount;

    public Meat(String name, int size, int extraMeatCount) {
        super(name, 0); // We'll calculate the price below
        this.size = size;
        this.extraMeatCount = extraMeatCount;
        this.price = calculatePrice(size, extraMeatCount);
    }

    private double calculatePrice(int size, int extraMeatCount) {
        int index = sizes.indexOf(size);
        if (index == -1) {
            return 0; // Invalid size
        }

        double basePrice = basePrices.get(index);
        double extraPrice = extraPrices.get(index);
        return basePrice + (extraPrice * extraMeatCount);
    }

    public int getSize() {
        return size;
    }

    public int getExtraMeatCount() {
        return extraMeatCount;
    }

    @Override
    public String toString() {
        return getName() + " (" + size + "\") with " + extraMeatCount + " extra meat - $" +
                String.format("%.2f", getPrice());
    }
}

