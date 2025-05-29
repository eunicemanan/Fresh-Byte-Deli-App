package com.pluralsight.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Represents available toppings for a sandwich, including cheese.
 */
public class Toppings {

    // Lists of different topping categories
    private static final ArrayList<String> cheeses = new ArrayList<>(Arrays.asList("American", "Provolone", "Cheddar", "Swiss"));
    private static final ArrayList<String> freeToppings = new ArrayList<>(Arrays.asList(
            "Lettuce", "Tomato", "Onions", "Peppers", "Pickles", "Olives"));

    private final ArrayList<String> selectedToppings;
    private final int sandwichSize; // Size in inches (4, 8, 12)

    /**
     * Constructor initializes the toppings list.
     *
     * @param sandwichSize The size of the sandwich (4, 8, or 12 inches).
     */
    public Toppings(int sandwichSize) {
        if (sandwichSize != 4 && sandwichSize != 8 && sandwichSize != 12) {
            throw new IllegalArgumentException("Invalid sandwich size: " + sandwichSize);
        }
        this.sandwichSize = sandwichSize;
        this.selectedToppings = new ArrayList<>();
    }

    /**
     * Displays all available free toppings.
     */
    public static void displayValidToppings() {
        System.out.println("Available Toppings: " + String.join(", ", freeToppings));
    }

    /**
     * Prompts the user for toppings input and returns a validated list of toppings.
     * <p>
     * This method displays the available free toppings, allows the user to enter
     * a comma‐separated list, validates each topping using equalsIgnoreCase against
     * the allowed list, and if any topping is invalid, it shows an error message and
     * re-prompts the user.
     * </p>
     *
     * @param input A Scanner object for user input.
     * @return An ArrayList of validated toppings.
     */
    public static ArrayList<String> getToppings(Scanner input) {
        ArrayList<String> selectedToppings = new ArrayList<>();
        while (true) {
            displayValidToppings();
            System.out.print("Enter toppings separated by commas (or press Enter for none): ");
            String inputToppings = input.nextLine().trim();

            // If the user doesn't enter any toppings, return an empty list.
            if (inputToppings.isEmpty()) {
                break;
            }

            String[] toppingArray = inputToppings.split(",");
            selectedToppings.clear(); // Start fresh for each iteration
            boolean allValid = true;

            for (String topping : toppingArray) {
                String trimmedTopping = topping.trim();
                boolean matchFound = false;
                // Validate using equalsIgnoreCase against the free toppings list.
                for (String valid : freeToppings) {
                    if (valid.equalsIgnoreCase(trimmedTopping)) {
                        selectedToppings.add(valid);
                        matchFound = true;
                        break;
                    }
                }
                if (!matchFound) {
                    System.out.println("❌ Invalid topping: \"" + trimmedTopping + "\". Please choose only from the available options.");
                    allValid = false;
                    break; // Stop checking further; re-prompt the user.
                }
            }
            if (allValid) {
                break; // All entered toppings are valid.
            } else {
                System.out.println("Redirecting to topping options...\n");
            }
        }
        return selectedToppings;
    }
    /**
     * Determines if a topping is valid by checking if it's in the cheeses list or free toppings list.
     *
     * @param topping The topping to validate.
     * @return true if the topping is valid (ignoring case), false otherwise.
     */
    private static boolean isValidTopping(String topping) {
        for (String cheese : cheeses) {
            if (cheese.equalsIgnoreCase(topping)) {
                return true;
            }
        }
        for (String free : freeToppings) {
            if (free.equalsIgnoreCase(topping)) {
                return true;
            }
        }
        return false;
    }

    public double getCheesePrice() {
        double basePrice = (sandwichSize == 4) ? 0.75 : (sandwichSize == 8) ? 1.50 : 2.25;
        double extraCheesePrice = (sandwichSize == 4) ? 0.30 : (sandwichSize == 8) ? 0.60 : 0.90;

        long cheeseCount = selectedToppings.stream().filter(topping -> {
            for (String cheese : cheeses) {
                if (cheese.equalsIgnoreCase(topping)) {
                    return true;
                }
            }
            return false;
        }).count();

        return (cheeseCount > 1) ? (basePrice + ((cheeseCount - 1) * extraCheesePrice))
                : (cheeseCount > 0 ? basePrice : 0.00);
    }

    /**Prompts the user to select a cheese type from valid options.
     @param input A Scanner object for user input.
     @return The selected cheese type (properly formatted).
     */
    public static String getCheeseTypes(Scanner input) {
        ArrayList<String> validCheeses = new ArrayList<>(Arrays.asList("American", "Provolone", "Cheddar", "Swiss"));

        while (true) {
            System.out.println("\n🧀 Available Cheese Options: " + String.join(", ", validCheeses));
            System.out.print("👉 Enter cheese choice: ");
            String cheeseChoice = input.nextLine().trim();

            for (String validCheese : validCheeses) {
                if (validCheese.equalsIgnoreCase(cheeseChoice)) {
                    return validCheese; // Ensures correct formatting
                }
            }
            System.out.println("❌ Invalid cheese choice. Please try again.");
        }
    }

    /** Asks the user if they want extra cheese.
     @param input A Scanner object for user input.
      @return true if extra cheese is desired, false otherwise.
     */
    public static boolean getExtraCheese(Scanner input) {
        while (true) {
            System.out.println("\n❓ Do you want extra cheese? (yes/no): ");
            String response = input.nextLine().trim();
            if (response.equalsIgnoreCase("yes")) {
                return true;
            } else if (response.equalsIgnoreCase("no")) {
                return false;
            } else {
                System.out.println("❌ Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }

    /**
     * Returns the list of selected toppings. @return An ArrayList of selected toppings.*/

    public ArrayList<String> getSelectedToppings() {
        return selectedToppings;
    }

    /**
     Returns a formatted string representation of the toppings and cheese cost.
     @return A string representing the selected toppings and the cheese cost.**/
    @Override
    public String toString() {
        return String.format("Toppings: %s | Cheese Cost: $%.2f", selectedToppings, getCheesePrice());
    }
}

