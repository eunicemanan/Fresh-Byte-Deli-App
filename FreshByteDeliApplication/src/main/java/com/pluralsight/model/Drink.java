package com.pluralsight.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Drink extends MenuItem {
    private final String size;
    private final String flavor;

    private static final ArrayList<String> drinkFlavors = new ArrayList<>(Arrays.asList("Cola", "Lemonade", "Iced Tea", "Fruit Punch"));

    public Drink(String name, String size, String flavor) {
        super(name, determinePrice(size)); // Pass correct price to parent class

        this.size = validateSize(size);
        this.flavor = validateFlavor(flavor); // Ensure valid flavor
    }

    /**
     * Determines the price based on size.
     */
    private static double determinePrice(String size) {
        if ("small".equalsIgnoreCase(size))
            return 2.00;
        if ("medium".equalsIgnoreCase(size))
            return 2.50;
        if ("large".equalsIgnoreCase(size))
            return 3.00;
        throw new IllegalArgumentException("❌ Invalid size: " + size);
    }

    private static String validateFlavor(String flavor) {
        for (String validFlavor : drinkFlavors) {
            if (validFlavor.equalsIgnoreCase(flavor.trim())) { // Ensure case is ignored & remove extra spaces
                return validFlavor; // Return correctly formatted flavor
            }
        }
        throw new IllegalArgumentException("❌ Invalid drink flavor: " + flavor);
    }
    private static String validateSize(String size) {
        if (size.equalsIgnoreCase("Small") || size.equalsIgnoreCase("Medium") || size.equalsIgnoreCase("Large")) {
            return size;
        }
        throw new IllegalArgumentException("❌ Invalid size: " + size);
    }




    public String getSize() {
        return size;
    }

    public String getFlavor() {
        return flavor;
    }

    /**
     * Displays all available drink flavors.
     */
    public static void displayFlavors() {
        System.out.println("\n🔹 Available Drink Flavors:");
        for (String flavor : drinkFlavors) {
            System.out.println("👉 " + flavor);
        }

    }

    /**
     * Checks if a given flavor is valid.
     */
    public static boolean isValidFlavor(String flavor) {
        for (String validFlavor : drinkFlavors) {
            if (validFlavor.equalsIgnoreCase(flavor)) {
                return true; // Match found, return true
            }
        }
        return false; // No match found
    }

    @Override
    public String toString() {
        return String.format("size\t%s\nflavor\t%s\nprice\t$%.2f", size, flavor, getPrice());
    }
    public static boolean isValidSize(String size) {
        return size.equalsIgnoreCase("Small") || size.equalsIgnoreCase("Medium") || size.equalsIgnoreCase("Large");
    }

}
