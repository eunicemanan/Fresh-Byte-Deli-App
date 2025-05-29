package com.pluralsight.model.menu;

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

        // Efficiently parse string input to enum using Stream
        public static Size fromString(String size) {
            if (size == null) return null;
            return switch (size.toLowerCase()) {
                case "small" -> SMALL;
                case "medium" -> MEDIUM;
                case "large" -> LARGE;
                default -> null;
            };
        }
    }

    private final Size size;
    private final String flavor;

    public Drinks(String flavor, String sizeStr) {
        super(flavor + " Drink", getValidatedPrice(sizeStr)); // Pass correct price to superclass

        this.size = Size.fromString(sizeStr);
        if (this.size == null) throw new IllegalArgumentException("Invalid drink size: " + sizeStr);

        this.flavor = flavor;
    }

    private static double getValidatedPrice(String sizeStr) {
        Size size = Size.fromString(sizeStr);
        if (size == null) throw new IllegalArgumentException("Invalid size: " + sizeStr);
        return size.getPrice();
    }

    public Size getSize() {
        return size;
    }

    public String getFlavor() {
        return flavor;
    }

    @Override
    public String toString() {
        return size.name().charAt(0) + size.name().substring(1).toLowerCase() +
                " " + flavor + " Drink - $" + String.format("%.2f", getPrice());
    }
}


