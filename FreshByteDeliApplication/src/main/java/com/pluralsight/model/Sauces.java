package com.pluralsight.model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a sauce selection in the menu.
 */
public class Sauces extends MenuItem {

    // Static list of available sauce flavors
    private static final ArrayList<String> sauceFlavors = new ArrayList<>(
            Arrays.asList("Mayo", "Mustard", "Ketchup", "Ranch", "Thousand Island", "Vinaigrette")
    );

    private final String selectedSauce;

    /**
     * Constructor for Sauces.
     * @param selectedSauce The user's chosen sauce.
     */
    public Sauces(String selectedSauce) {
        super(selectedSauce + " Sauce", 0.50);

        if (!sauceFlavors.contains(selectedSauce)) {
            throw new IllegalArgumentException("Invalid sauce selection: " + selectedSauce);
        }

        this.selectedSauce = selectedSauce;
    }

    public String getSelectedSauce() {
        return selectedSauce;
    }

    /**
     * Prints available sauces.
     */
    public static void printAvailableSauces() {
        System.out.println("Available Sauces: " + sauceFlavors);
    }

    @Override
    public String toString() {
        return String.format("%s - $%.2f", getName(), getPrice());
    }
}


