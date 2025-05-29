package com.pluralsight.model;

public class Chip {
    // Removed the 'type' field
    private final double price; // The only field, and it's final

    // Constructor: Now it only takes the price
    public Chip(double price) {
        this.price = price;
    }

    // Getter for the price (still needed as 'price' is private)
    public double getPrice() {
        return price;
    }

    // No need for getType() anymore

    @Override
    public String toString() {
        // The toString() method now just prints "Chips" and the price
        return String.format("Chips ($%.2f)", price);
    }
}
