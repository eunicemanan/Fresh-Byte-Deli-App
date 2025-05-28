package com.pluralsight.model;

import com.pluralsight.model.menu.MenuItem;

public class Drinks extends MenuItem {

    public enum Size {
        SMALL(2.00),
        MEDIUM(2.50),
        LARGE(3.00);

        private final double price;

        Size(double price) {
            this.price = price;
        }

        public double getPrice() {
            return price;
        }

        // Optional: parse string to enum (case-insensitive)
        public static Size fromString(String size) {
            if (size == null) return null;
            switch(size.toLowerCase()) {
                case "small":
                    return SMALL;
                case "medium":
                    return MEDIUM;
                case "large":
                    return LARGE;
                default:
                    return null;
            }
        }
    }

    // Instance fields
    private Size size;
    private String flavor;

    public Drinks(String name, String sizeStr, String flavor) {
        super(name, 0); // price will be calculated
        this.size = Size.fromString(sizeStr);
        this.flavor = flavor;
        this.price = (this.size != null) ? this.size.getPrice() : 0;
    }

    public Size getSize() {
        return size;
    }

    public String getFlavor() {
        return flavor;
    }

    @Override
    public String toString() {
        return getName() + " (" + (size != null ? size.name().charAt(0) + size.name().substring(1).toLowerCase() : "Unknown") +
                ", " + flavor + ") - $" + String.format("%.2f", getPrice());
    }
}

