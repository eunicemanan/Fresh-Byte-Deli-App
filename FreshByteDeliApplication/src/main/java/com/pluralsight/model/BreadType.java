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
            case "wrap":
                return new BreadType("Wrap");
            default:
                return null;
        }
    }

    public static String getBreadType (Scanner input) {
        while (true) {
            System.out.print("\nEnter bread type (White, Wheat, Rye, or a Wrap): ");
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

