package com.pluralsight.model;

import java.util.ArrayList;
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
     * Prompts for and returns a valid sandwich size from user input.
     */
    public static String getValidSandwichSize() {
        while (true) {
            System.out.print("\n Enter sandwich size in inches (4\", 8\", 12\"): ");
            String size = input.nextLine().trim();
            if (size.equals("4") || size.equals("8") || size.equals("12")) {
                return size;
            }
            System.out.println("❌ Invalid size. Please try again.");
        }
    }

    /**
     * Updated toString method that displays the sandwich details in a bordered box.
     */
    @Override
    public String toString() {

        String divider = "-----------------------------------------";

        String sauceStr = sauces.isEmpty() ? "None" : String.join(", ", sauces);
        String toppingStr = toppings.isEmpty() ? "None" : String.join(", ", toppings);

        StringBuilder sb = new StringBuilder();

        // Top divider
        sb.append(divider).append("\n");

        // Sandwich details
        sb.append("Sandwich: ").append(getName()).append("\n");
        sb.append("Price: $").append(String.format("%.2f", getPrice())).append("\n");
        sb.append("Size: ").append(size).append(" inches").append("\n");
        sb.append("Meat: ").append(meat).append("\n");
        sb.append("Extra Meat: ").append(extraMeat ? "Yes" : "No").append("\n");
        sb.append("Cheese: ").append(cheese).append("\n");
        sb.append("Extra Cheese: ").append(extraCheese ? "Yes" : "No").append("\n");
        sb.append("Bread: ").append(bread).append("\n");
        sb.append("Sauces: ").append(sauceStr).append("\n");
        sb.append("Toppings: ").append(toppingStr).append("\n");
        sb.append("Toasted: ").append(isToasted ? "Yes" : "No").append("\n");

        // Bottom divider
        sb.append(divider).append("\n");

        return sb.toString();
    }


    public double getPrice() {
        return price;
    }
}


