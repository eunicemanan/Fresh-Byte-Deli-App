package com.pluralsight.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Represents a sauce selection in the menu.
 */
public class Sauces extends MenuItem {

    private static final String RESET = "\033[0m";
    private static final String GREEN = "\033[32m";

    // Static list of allowed sauce flavors with proper capitalization
    private static final ArrayList<String> sauceFlavors = new ArrayList<>(
            Arrays.asList("Mayo", "Mustard", "Ketchup", "Ranch", "Thousand Island", "Vinaigrette")
    );

    private final String selectedSauce;

    public Sauces(String selectedSauce) {
        super(selectedSauce + "Sauce", 0);

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
        System.out.println("Available Sauces: " + GREEN + String.join(", ", sauceFlavors) + RESET);
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
    public static boolean getExtraSauce(Scanner input) {
        while (true) {
            System.out.println("");
            printAvailableSauces();
            System.out.print("Do you want extra sauce? (yes/no): ");
            String response = input.nextLine().trim();

            // If the user provides no input, we'll default to no extra sauce.
            if (response.isEmpty()) {
                return false;
            }

            if (response.equalsIgnoreCase("yes")) {
                return true;
            } else if (response.equalsIgnoreCase("no")) {
                return false;
            } else {
                System.out.println("❌ Invalid input: \"" + response + "\". Please enter 'yes' or 'no'.");
                System.out.println("Redirecting to sauce options...\n");
            }
        }
    }


    @Override
    public String toString() {
        return String.format("%s - $%.2f", getName(), getPrice());
    }
}


