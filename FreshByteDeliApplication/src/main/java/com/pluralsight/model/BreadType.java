package com.pluralsight.model;

import java.util.Scanner;

public class BreadType {
    private String type;

    public BreadType(String type) {
        this.type = type;
    }

    public static BreadType fromString(String breadTypeStr) {
        if (breadTypeStr == null) {
            return null;
        }
        switch (breadTypeStr.trim().toLowerCase()) {
            case "white":
                return new BreadType("White");
            case "wheat":
                return new BreadType("Wheat");
            case "rye":
                return new BreadType("Rye");
            case "sourdough":
                return new BreadType("Sourdough");
            case "multigrain":
                return new BreadType("Multigrain");
            default:
                return null;
        }
    }

    /**
     * Prompts the user to enter a bread type.
     * Valid inputs are "White", "Wheat", "Rye", "Sourdough", "Multigrain".
     * This method will repeatedly prompt until the user enters a valid value.
     *
     * @param input A Scanner object to read user input.
     * @return A valid BreadType instance.
     */
    public static String getBreadType (Scanner input) {
        while (true) {
            System.out.print("\n👉 Enter bread type (White, Wheat, Rye, Sourdough, Multigrain): ");
            String breadInput = input.nextLine().trim();
            BreadType breadType = BreadType.fromString(breadInput);
            if (breadType != null) {
                return breadInput;
            }
            System.out.println("❌ Invalid bread type. Please try again.");
        }
    }

    @Override
    public String toString() {
        return type;
    }
}

