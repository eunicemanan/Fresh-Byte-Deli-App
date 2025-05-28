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
    private static final ArrayList<String> freeToppings = new ArrayList<>(Arrays.asList("Lettuce", "Peppers", "Onions", "Tomatoes", "Jalapeños", "Cucumbers", "Pickles", "Guacamole", "Mushrooms"));

    private final ArrayList<String> selectedToppings;
    private final int sandwichSize; // Size in inches (4, 8, 12)

    /**
     * Constructor initializes toppings list.
     * @param sandwichSize The size of the sandwich (4, 8, 12).
     */
    public Toppings(int sandwichSize) {
        if (sandwichSize != 4 && sandwichSize != 8 && sandwichSize != 12) {
            throw new IllegalArgumentException("Invalid sandwich size: " + sandwichSize);
        }

        this.sandwichSize = sandwichSize;
        this.selectedToppings = new ArrayList<>();
    }

    /**
     * Adds a topping if it's valid.
     * @param topping The topping to add.
     */
    public void addTopping(String topping) {
        if (isValidTopping(topping)) {
            selectedToppings.add(topping);
        } else {
            throw new IllegalArgumentException("Invalid topping: " + topping);
        }
    }

    /**
     * Determines if a topping is valid.
     */
    private static boolean isValidTopping(String topping) {
        return cheeses.contains(topping) || freeToppings.contains(topping);
    }

    /**
     * Calculates total price for cheese toppings (free toppings have no cost).
     */
    public double getCheesePrice() {
        double basePrice = (sandwichSize == 4) ? 0.75 : (sandwichSize == 8) ? 1.50 : 2.25;
        double extraCheesePrice = (sandwichSize == 4) ? 0.30 : (sandwichSize == 8) ? 0.60 : 0.90;

        long cheeseCount = selectedToppings.stream().filter(cheeses::contains).count();
        return (cheeseCount > 1) ? (basePrice + ((cheeseCount - 1) * extraCheesePrice)) : (cheeseCount > 0 ? basePrice : 0.00);
    }
    public static String getCheeseTypes(Scanner input) {
        ArrayList<String> validCheeses = new ArrayList<>(Arrays.asList("American", "Provolone", "Cheddar", "Swiss"));

        while (true) {
            System.out.println("\n🧀 Available Cheese Options: " + String.join(", ", validCheeses));
            System.out.print("👉 Enter cheese choice: ");
            String cheeseChoice = input.nextLine().trim();

            for (String validCheese : validCheeses) {
                if (validCheese.equalsIgnoreCase(cheeseChoice)) {
                    return validCheese; // ✅ Ensures correct formatting, regardless of input case
                }
            }

            System.out.println("❌ Invalid cheese choice. Please try again.");
        }
    }


    /**
     * Returns a list of selected toppings.
     */
    public ArrayList<String> getSelectedToppings() {
        return selectedToppings;
    }



    /**
     * Returns a formatted string representation of the toppings.
     */
    @Override
    public String toString() {
        return String.format("Toppings: %s | Cheese Cost: $%.2f", selectedToppings, getCheesePrice());
    }
}
