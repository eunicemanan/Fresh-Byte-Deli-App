package com.pluralsight.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Sandwich extends MenuItem {
    private String size;
    private String meat;
    private String cheese;
    private boolean extraMeat;
    private boolean extraCheese;
    private String bread;
    private final ArrayList<String> sauces;
    private final ArrayList<String> toppings;
    private boolean isToasted;

    private static final ArrayList<String> validMeats = new ArrayList<>(Arrays.asList("Turkey", "Ham", "Roast Beef", "Chicken", "Salami"));
    private static final ArrayList<String> validCheeses = new ArrayList<>(Arrays.asList("American", "Cheddar", "Swiss", "Provolone"));
    private static final ArrayList<String> validBreads = new ArrayList<>(Arrays.asList("White", "Wheat", "Rye", "Sourdough"));
    private static final ArrayList<String> validSauces = new ArrayList<>(Arrays.asList("Mayo", "Mustard", "Ketchup", "Ranch"));
    private static final ArrayList<String> validToppings = new ArrayList<>(Arrays.asList("Lettuce", "Tomato", "Onions", "Peppers", "Pickles", "Olives"));

    private static Scanner input = new Scanner(System.in);

    public Sandwich(String name, double price, String size, String meat, String cheese,
                    boolean extraMeat, boolean extraCheese, String bread,
                    ArrayList<String> sauces, ArrayList<String> toppings, boolean isToasted) {
        super(name, price);
        this.size = size;
        this.meat = meat;
        this.cheese = cheese;
        this.extraMeat = extraMeat;
        this.extraCheese = extraCheese;
        this.bread = bread;
        this.sauces = sauces;
        this.toppings = toppings;
        this.isToasted = isToasted;
    }

    /**
     * Calculates the price based on sandwich size and extras.
     */
    private static double calculatePrice(String size, boolean extraMeat, boolean extraCheese) {
        double basePrice = switch (size) {
            case "4" -> 5.5;
            case "8" -> 7.0;
            case "12" -> 8.5;
            default -> throw new IllegalArgumentException("❌ Invalid size: " + size);
        };

        basePrice += extraMeat ? 1.5 : 0.0;
        basePrice += extraCheese ? 0.9 : 0.0;
        return basePrice;
    }

    /**
     * Constructor for a custom sandwich using the calculated price.
     */
    public Sandwich(String size, String meat, String cheese, boolean extraMeat, boolean extraCheese,
                    String bread, boolean isToasted, ArrayList<String> sauces, ArrayList<String> toppings) {
        this("Custom Sandwich", calculatePrice(size, extraMeat, extraCheese), size,
                meat.isEmpty() ? "None" : meat,
                cheese.isEmpty() ? "None" : cheese,
                extraMeat, extraCheese,
                bread.isEmpty() ? "None" : bread,
                sauces, toppings,
                isToasted);
    }

    /**
     * Validates the sandiwch size.
     */
    private static String validateSize(String size) {
        if (size.equals("4") || size.equals("8") || size.equals("12")) {
            return size;
        }
        throw new IllegalArgumentException("❌ Invalid size: " + size);
    }

    /**
     * Prompts and returns a valid sandwich size from user input.
     */
    public static String getValidSandwichSize() {
        while (true) {
            System.out.print("\n👉 Enter sandwich size in inches (4\", 8\", 12\"): ");
            String size = input.nextLine().trim();
            if (size.equals("4") || size.equals("8") || size.equals("12")) {
                return size;
            }
            System.out.println("❌ Invalid size. Please try again.");
        }
    }

    /**
     * Updated toString method that neatly appends all sandwich details.
     */
    @Override
    public String toString() {
        String sauceStr = (sauces.isEmpty()) ? "None" : String.join(", ", sauces);
        String toppingStr = (toppings.isEmpty()) ? "None" : String.join(", ", toppings);
        return String.format(
                "Sandwich: %s\n" +
                        "Price       : $%.2f\n" +
                        "Size        : %s inches\n" +
                        "Meat        : %s\n" +
                        "Extra Meat  : %s\n" +
                        "Cheese      : %s\n" +
                        "Extra Cheese: %s\n" +
                        "Bread       : %s\n" +
                        "Sauces      : %s\n" +
                        "Toppings    : %s\n" +
                        "Toasted     : %s",
                getName(),
                getPrice(),
                size,
                meat,
                extraMeat ? "Yes" : "No",
                cheese,
                extraCheese ? "Yes" : "No",
                bread,
                sauceStr,
                toppingStr,
                isToasted ? "Yes" : "No"
        );
    }


    public double getPrice() {
        return price;
    }
}

