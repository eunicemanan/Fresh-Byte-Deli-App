package com.pluralsight.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Represents a meat selection in the sandwich order.
 */
public class Meat extends MenuItem {

    // List of valid meat types
    private static final ArrayList<String> meatTypes = new ArrayList<>(Arrays.asList(
            "Steak", "Ham", "Salami", "Roast Beef", "Chicken", "Bacon"
    ));

    // Valid sizes and their corresponding prices
    private static final ArrayList<Integer> sizes = new ArrayList<>(Arrays.asList(4, 8, 12));
    private static final ArrayList<Double> basePrices = new ArrayList<>(Arrays.asList(1.00, 2.00, 3.00));
    private static final ArrayList<Double> extraPrices = new ArrayList<>(Arrays.asList(0.50, 1.00, 1.50));

    private final int size;  // Size of the meat portion
    private final int extraMeatCount;  // Number of extra meat servings

    /**
     * Constructor for Meat.
     * @param name The meat type.
     * @param size The sandwich size (4", 8", 12").
     * @param extraMeatCount The number of extra meat servings.
     */
    public Meat(String name, int size, int extraMeatCount) {
        super(name, calculatePrice(size, extraMeatCount)); // Fix: Correct price calculation

        if (!meatTypes.contains(name)) {
            throw new IllegalArgumentException("Invalid meat type: " + name);
        }
        if (!sizes.contains(size)) {
            throw new IllegalArgumentException("Invalid size: " + size);
        }

        this.size = size;
        this.extraMeatCount = extraMeatCount;
    }

    /**
     * Calculates the price based on size and extra meat servings.
     */
    private static double calculatePrice(int size, int extraMeatCount) {
        int index = sizes.indexOf(size);
        if (index == -1) return 0.00;  // Handles invalid sizes safely

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

    public static String getMeatTypes(Scanner input) {
        ArrayList<String> validMeats = new ArrayList<>(Arrays.asList("Turkey", "Ham", "Roast Beef", "Chicken", "Salami"));

        while (true) {
            System.out.println("\n🥩 Available Meat Options: " + String.join(", ", validMeats));
            System.out.print("👉 Enter meat choice: ");
            String meatChoice = input.nextLine().trim();

            for (String validMeat : validMeats) {
                if (validMeat.equalsIgnoreCase(meatChoice)) {
                    return validMeat; // ✅ Ensures correct format, regardless of input case
                }
            }

            System.out.println("❌ Invalid meat choice. Please try again.");
        }
    }

    /**
     * Prompts the user to decide if they want extra meat.
     *
     * @param input A Scanner object to read user input.
     * @return true if the user wants extra meat; false otherwise.
     */
    public static boolean getExtraMeat(Scanner input) {
        while (true) {
            System.out.print("Do you want extra meat? (yes/no): ");
            String response = input.nextLine().trim();
            if (response.equalsIgnoreCase("yes")) {
                return true;
            } else if (response.equalsIgnoreCase("no")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }

    @Override
    public String toString() {
        return String.format("%s (%d\")%s - $%.2f",
                getName(),
                size,
                (extraMeatCount > 0 ? " with " + extraMeatCount + "x extra meat" : ""),
                getPrice());
    }
}

