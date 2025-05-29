package com.pluralsight.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Represents a sauce selection in the menu.
 */
public class Sauces extends MenuItem {

    // Static list of allowed sauce flavors with proper capitalization
    private static final ArrayList<String> sauceFlavors = new ArrayList<>(
            Arrays.asList("Mayo", "Mustard", "Ketchup", "Ranch", "Thousand Island", "Vinaigrette")
    );

    private final String selectedSauce;

    /**
     * Constructor for Sauces.
     *
     * @param selectedSauce The user's chosen sauce.
     */
    public Sauces(String selectedSauce) {
        super(selectedSauce + " Sauce", 0.50);

        if (!containsSauce(selectedSauce)) {
            throw new IllegalArgumentException("Invalid sauce selection: " + selectedSauce);
        }

        this.selectedSauce = selectedSauce;
    }

    private static boolean containsSauce(String sauce) {
        for (String validSauce : sauceFlavors) {
            if (validSauce.equalsIgnoreCase(sauce)) {
                return true;
            }
        }
        return false;
    }

    public static void printAvailableSauces() {
        System.out.println("\nAvailable Sauces: " + String.join(", ", sauceFlavors));
    }

    public static ArrayList<String> getSauces(Scanner input) {
        ArrayList<String> selectedSauces = new ArrayList<>();
        while (true) {
            System.out.println(" ");
            printAvailableSauces();
            System.out.print("Enter sauces separated by commas (or press Enter for none): ");
            String inputSauces = input.nextLine().trim();

            // If no sauces entered, return an empty list.
            if (inputSauces.isEmpty()) {
                break;
            }

            String[] sauceArray = inputSauces.split(",");
            selectedSauces.clear(); // Clear previous entries for a fresh attempt.
            boolean allValid = true;

            for (String sauce : sauceArray) {
                String trimmedSauce = sauce.trim();
                boolean matchFound = false;
                for (String validSauce : sauceFlavors) {
                    if (validSauce.equalsIgnoreCase(trimmedSauce)) {
                        selectedSauces.add(validSauce);  // Use the properly formatted sauce.
                        matchFound = true;
                        break;
                    }
                }
                if (!matchFound) {
                    System.out.println("❌ Invalid sauce: \"" + trimmedSauce + "\". Please choose only from the available options.");
                    allValid = false;
                    break; // Stop checking further; re-prompt the user.
                }
            }

            if (allValid) {
                break;
            } else {
                System.out.println("Redirecting to sauce options...\n");
            }
        }
        return selectedSauces;
    }

    @Override
    public String toString() {
        return String.format("%s - $%.2f", getName(), getPrice());
    }
}


