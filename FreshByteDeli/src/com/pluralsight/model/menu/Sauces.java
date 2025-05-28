package com.pluralsight.model;

import java.util.ArrayList;
import java.util.Arrays;

//  It extends MenuItem so it inherits name and price fields.
public class Sauces extends MenuItem {

    // Static list of available sauce flavors that all instances of Sauces can use.
    private static final ArrayList<String> sauceFlavors = new ArrayList<>(
            Arrays.asList("Mayo", "Mustard", "Ketchup", "Ranch", "Thousand Island", "Vinaigrette")
    );

    // Instance variable to store the user's selected sauce(s).
    private String selectedSauces;

    // Constructor to create a new Sauces object with name, price, and selected sauces.
    public Sauces(String name, double price, String selectedSauces) {
        super(name, price); // Call the parent MenuItem constructor to set name and price.
        this.selectedSauces = selectedSauces; // Set the sauces chosen by the user.
    }

    // Getter method to retrieve the selected sauces.
    public String getSelectedSauces() {
        return selectedSauces;
    }

    // Static method to print the list of available sauce options to the console.
    public static void printAvailableSauces() {
        System.out.println("Available Sauces:");
        for (String flavor : sauceFlavors) {
            System.out.println("🧂 " + flavor);
        }
    }

    // toString override to display sauce item details in a readable format.
    @Override
    public String toString() {
        return getName() + ": " + selectedSauces + " - $" + String.format("%.2f", getPrice());
    }
}
