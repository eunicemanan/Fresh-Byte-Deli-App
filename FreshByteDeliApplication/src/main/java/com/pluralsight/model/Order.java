package com.pluralsight.model;

import java.util.ArrayList;
import java.util.Scanner;

public class Order {
    private final ArrayList<Sandwich> sandwiches;
    private final ArrayList<Drink> drinks;
    private final ArrayList<Chip> chips;

    private static Scanner input = new Scanner(System.in);
    private static final String RESET = "\033[0m";
    private static final String RED = "\033[31m";
    private static final String GREEN = "\033[32m";

    /**
     * Constructor initializes empty ArrayLists for order items.
     */
    public Order() {
        this.sandwiches = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.chips = new ArrayList<>();
    }

    public ArrayList<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public ArrayList<Drink> getDrinks() {
        return drinks;
    }

    public ArrayList<Chip> getChips() {
        return chips;
    }

    /**
     * Calculates the total price of all items in the order.
     */
    public double getTotal() {
        double total = 0.0;
        for (Sandwich sandwich : sandwiches) {
            total += sandwich.getPrice();
        }
        for (Drink drink : drinks) {
            total += drink.getPrice();
        }
        for (Chip chip : chips) {
            total += chip.getPrice();
        }
        return total;
    }

    /** Returns a formatted receipt string with sections for sandwiches, drinks, and chips. */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
       // 10 spaces indent
        String divider = "-----------------------------------------";

        // Order Header
        sb.append("==== Order Details ====\n\n");

        // Sandwiches Section
        sb.append("  Sandwiches:\n");

        if (sandwiches.isEmpty()) {
            sb.append("No Sandwiches Added.\n\n");
        } else {
            int sandwichCount = 0;
            for (Sandwich s : sandwiches) {
                sandwichCount++;
                sb.append("Custom Sandwich #").append(sandwichCount);
                String[] lines = cleanAndSplit(s.toString());
                for (String line : lines) {
                    sb.append("  ").append(line).append("\n");
                }
                sb.append("\n");
            }
        }

        // Drinks Section
        sb.append("Drinks:\n");
        sb.append(divider).append("\n");
        if (drinks.isEmpty()) {
            sb.append("No Drinks Added.\n\n");
        } else {
            for (Drink d : drinks) {
                String[] lines = cleanAndSplit(d.toString());
                for (String line : lines) {
                    sb.append("  ").append(line).append("\n");
                }
                sb.append("\n");
            }
        }

        // Chips Section
        sb.append("Chips:\n");
        sb.append(divider).append("\n");
        if (chips.isEmpty()) {
            sb.append("No Chips Added.\n\n");
        } else {
            for (Chip c : chips) {
                String[] lines = cleanAndSplit(c.toString());
                for (String line : lines) {
                    sb.append("  ").append(line).append("\n");
                }
                sb.append("\n");
            }
        }

        // Order Total
        sb.append(divider + "\n")
                .append(GREEN + "       Order Total: $")
                .append(String.format("%.2f", getTotal()))
                .append("\n" + RESET);

        return sb.toString();
    }

    /** Removes any leading whitespace on each line
     and then splits the text on newlines. */
    private String[] cleanAndSplit(String text) {
        // Remove leading whitespace from each line (multiline mode)
        String cleaned = text.replaceAll("(?m)^\\s+", "");
        return cleaned.split("\n");
    }
}
