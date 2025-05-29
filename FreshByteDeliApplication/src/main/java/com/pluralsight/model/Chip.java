package com.pluralsight.model;

import java.util.Arrays;
import java.util.List;

public class Chip {
    private String type;
    private final double price;

    public Chip(String type) {
        this.type = type;
        this.price = 1.5;
    }

    @Override
    public String toString() {
        return String.format("Type:\t%s\nPrice:\t$%.2f", type, price);
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns the list of available chip flavors.
     *
     * @return a list of chip flavors.
     */
    public static List<String> getChipFlavors() {
        return Arrays.asList("Classic", "Barbecue", "Sour Cream & Onion");
    }

    /**
     * Displays all available chip flavors.
     */
    public static void displayFlavors() {
        System.out.println("Available Chip Flavors:");
        for (String flavor : getChipFlavors()) {
            System.out.println("- " + flavor);
        }
    }
}
