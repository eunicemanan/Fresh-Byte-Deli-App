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
    private boolean extraVegetables;
    private boolean isToasted;
    private boolean extraSauce;

    private static Scanner input = new Scanner(System.in);

    // Constructor with 11 parameters:
    public Sandwich(String bread, String size, String meat, String cheese,
                    boolean extraMeat, boolean extraCheese, boolean extraVegetables,
                    boolean isToasted, ArrayList<String> sauces, ArrayList<String> toppings, boolean b) {
        // FIX: Reverse the order of arguments in the super call to match MenuItem's constructor.
        super("Custom Sandwich", calculatePrice(size, extraMeat, extraCheese));

        this.size = size;
        this.meat = meat.isEmpty() ? "None" : meat;
        this.cheese = cheese.isEmpty() ? "None" : cheese;
        this.extraMeat = extraMeat;
        this.extraCheese = extraCheese;
        this.bread = bread.isEmpty() ? "None" : bread;
        this.isToasted = isToasted;
        this.sauces = (sauces != null) ? new ArrayList<>(sauces) : new ArrayList<>();
        this.toppings = (toppings != null) ? new ArrayList<>(toppings) : new ArrayList<>();
        this.extraVegetables = extraVegetables;
        this.extraSauce = extraSauce;
    }

    // Price calculation ignores extra veg and extra sauce.
    private static double calculatePrice(String size, boolean extraMeat, boolean extraCheese) {
        double basePrice = switch (size) {
            case "4" -> 5.5;
            case "8" -> 7.0;
            case "12" -> 8.5;
            default -> throw new IllegalArgumentException("Invalid size: " + size);
        };

        basePrice += extraMeat ? 1.5 : 0.0;
        basePrice += extraCheese ? 0.9 : 0.0;
        return basePrice;
    }

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

    @Override
    public double getPrice() {
        return super.getPrice();
    }

    @Override
    public String toString() {
        String divider = "-----------------------------------------";
        String sauceStr = sauces.isEmpty() ? "None" : String.join(", ", sauces);
        String toppingStr = toppings.isEmpty() ? "None" : String.join(", ", toppings);

        StringBuilder sb = new StringBuilder();

        sb.append(divider).append("\n");
        sb.append("Sandwich: ").append(getName()).append("\n");
        sb.append("Price: $").append(String.format("%.2f", getPrice())).append("\n");
        sb.append("Size: ").append(size).append(" inches").append("\n");
        sb.append("Meat: ").append(meat).append("\n");
        sb.append("Extra Meat: ").append(extraMeat ? "Yes" : "No").append("\n");
        sb.append("Cheese: ").append(cheese).append("\n");
        sb.append("Extra Cheese: ").append(extraCheese ? "Yes" : "No").append("\n");
        sb.append("Bread: ").append(bread).append("\n");
        sb.append("Sauces: ").append(sauceStr).append("\n");
        sb.append("Extra Sauce: ").append(extraSauce ? "Yes" : "No").append("\n");
        sb.append("Toppings: ").append(toppingStr).append("\n");
        sb.append("Toasted: ").append(isToasted ? "Yes" : "No").append("\n");
        sb.append("Extra Veg: ").append(extraVegetables ? "Yes" : "No").append("\n");
        sb.append(divider).append("\n");

        return sb.toString();
    }
    // In Sandwich.java (inside the Sandwich class)
    public void setSauces(ArrayList<String> sauces) {
        this.sauces.clear();
        if (sauces != null) {
            this.sauces.addAll(sauces);
        }
    }

    public void setToppings(ArrayList<String> toppings) {
        this.toppings.clear();
        if (toppings != null) {
            this.toppings.addAll(toppings);
        }
    }

}


