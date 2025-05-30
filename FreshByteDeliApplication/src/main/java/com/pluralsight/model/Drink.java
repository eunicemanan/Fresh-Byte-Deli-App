package com.pluralsight.model;

public class Drink {
    private final String size;
    private final double price; // Changed to final because it's set in the constructor and shouldn't change

    public Drink(String size) {

        String validatedSize = validateSize(size);  // 1. Validate the incoming 'size' string immediately

        this.size = validatedSize;  // 2. Initialize the 'final' size field of THIS Drink object

        this.price = determinePrice(validatedSize); // 3. Determine and initialize the price for this Drink object
    }

    // --- Helper methods (static, as they don't depend on a specific Drink instance) ---
    private static double determinePrice(String size) {
        if ("small".equalsIgnoreCase(size))
            return 2.00;
        if ("medium".equalsIgnoreCase(size))
            return 2.50;
        if ("large".equalsIgnoreCase(size))
            return 3.00;
        throw new IllegalArgumentException("❌ Invalid size: " + size + ". This should not happen if validateSize was called.");
    }

    private static String validateSize(String size) {
        if (size == null || (!size.equalsIgnoreCase("Small") && !size.equalsIgnoreCase("Medium") && !size.equalsIgnoreCase("Large"))) {
            throw new IllegalArgumentException("❌ Invalid size: '" + size + "'. Must be Small, Medium, or Large.");
        }
        return size; // Return the original string
    }

    public double getPrice() {
        return price;
    }

    public static boolean isValidSize(String size) {
        return size != null && (size.equalsIgnoreCase("Small") || size.equalsIgnoreCase("Medium") || size.equalsIgnoreCase("Large"));
    }
    @Override
    public String toString() {
        return "Size: " + size + "\n" +
                "Price: $" + String.format("%.2f", getPrice());
    }


}