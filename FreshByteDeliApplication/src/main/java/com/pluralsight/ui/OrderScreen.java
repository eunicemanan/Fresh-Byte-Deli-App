package com.pluralsight.ui;

import java.util.Scanner;

import com.pluralsight.model.*;

public class OrderScreen extends HomeScreen {
    private static Scanner input = new Scanner(System.in);

    public static void startNewOrder() {
        Order order = new Order();
        System.out.println("\n🥪 Starting a new order...\n");

        boolean addingItems = true;

        while (addingItems) {
            System.out.println("\n📌 What would you like to add?");
            System.out.println("[1] 🥪 Sandwich");
            System.out.println("[2] 🥤 Drink");
            System.out.println("[3] 🍟 Chips");
            System.out.println("[4] ✅ Finish Order & Checkout");
            System.out.print("👉 Enter choice: ");

            if (input.hasNextInt()) {
                int choice = input.nextInt();
                input.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        order.getSandwiches().add(createSandwich());
                        break;

                    case 2:
                        order.getDrinks().add(createDrink()); // ✅ Now handled by `createDrink()`
                        break;

                    case 3:
                        order.getChips().add(createChips());
                        break;

                    case 4:
                        addingItems = false; // End selection process
                        break;

                    default:
                        System.out.println("❌ Invalid option. Try again.");
                        break;
                }
            } else {
                input.nextLine(); // Consume invalid input
                System.out.println("🚫 Invalid entry! Please enter a number.");
            }
        }

        System.out.println("\n🧾 Order Summary:\n" + order);
        System.out.println("✅ Thank you for ordering at Fresh Byte Deli!");
    }

    private static Sandwich createSandwich() {
        System.out.println("\n🥪 Creating Sandwich...");

        String size = Sandwich.getValidSandwichSize(); // ✅ Ensures valid size
        String meat = Meat.getMeatTypes(input); // ✅ Pass `input` from OrderScreen
        String cheese = Toppings.getCheeseTypes(input);
        boolean extraMeat = Meat.getExtraMeat(input);
        boolean extraCheese = getBooleanInput("❓ Extra Cheese? (true/false, or press Enter to skip): ");
        String bread = getOptionalInput("👉 Enter bread type (or press Enter to skip): ");
        boolean isToasted = getBooleanInput("❓ Toasted? (true/false, or press Enter to skip): ");

        return new Sandwich(size, meat, cheese, extraMeat, extraCheese, bread, isToasted);
    }

    private static Drink createDrink() {
        System.out.println("\n🥤 Creating Drink...");
        Drink.displayFlavors(); // ✅ Show available flavors

        boolean validDrink = false;
        String drinkSize = "";
        String drinkFlavor = "";

        while (!validDrink) {
            drinkFlavor = getOptionalInput("👉 Enter drink flavor: ").trim();

            if (Drink.isValidFlavor(drinkFlavor)) {
                drinkSize = getOptionalInput("👉 Enter drink size (Small, Medium, Large): ").trim();

                if (Drink.isValidSize(drinkSize)) {
                    validDrink = true; // ✅ Exit loop if valid
                } else {
                    System.out.println("❌ Invalid drink size. Please try again.");
                }
            } else {
                System.out.println("❌ Invalid drink option. Please try again.");
            }
        }

        return new Drink("Drink", drinkSize, drinkFlavor); // ✅ Creates drink with valid inputs
    }

    private static Chip createChips() {
        System.out.println("\n🍟 Creating Chips...");
        String flavor = getOptionalInput("👉 Enter chip flavor: ");

        return new Chip(flavor);
    }

    private static String getOptionalInput(String prompt) {
        System.out.print(prompt);
        return input.nextLine().trim(); // ✅ Allows skipping choices
    }

    private static boolean getBooleanInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String response = input.nextLine().trim().toLowerCase();

            if (response.isEmpty()) return false;
            if (response.equals("true") || response.equals("false")) return Boolean.parseBoolean(response);

            System.out.println("❌ Invalid response! Please enter 'true' or 'false', or press Enter to skip.");
        }
    }
}
