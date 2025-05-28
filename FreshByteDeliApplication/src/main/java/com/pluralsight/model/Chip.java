package com.pluralsight.model;

/**
 * Represents a chip selection in the menu.
 */
public class Chip {
    private String type;
    private final double price;

    /**
     * Constructor for Chip.
     * @param type The chip flavor.
     */
    public Chip(String type) {
        this.type = type;
        this.price = 1.5;
    }

    @Override
    public String toString() {
        return String.format("type\t%s\nprice\t$%.2f", type, price);
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

