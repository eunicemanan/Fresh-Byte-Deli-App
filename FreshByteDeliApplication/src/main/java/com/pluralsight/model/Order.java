package com.pluralsight.model;

import java.util.ArrayList;

public class Order {
    private final ArrayList<Sandwich> sandwiches;
    private final ArrayList<Drink> drinks;
    private final ArrayList<Chip> chips;

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

    /**
     * Returns a formatted string representation of the order details.
     */
    @Override
    public String toString() {
        return String.format(
                "---- Order Details ----\n" +
                        "---- Sandwiches ----\n%s\n" +
                        "---- Drinks ----\n%s\n" +
                        "---- Chips ----\n%s\n" +
                        "Order Total: $%.2f",
                sandwiches, drinks, chips, getTotal()
        );
    }
}

