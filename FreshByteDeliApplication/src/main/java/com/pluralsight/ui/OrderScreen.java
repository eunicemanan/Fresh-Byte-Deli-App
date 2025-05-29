package com.pluralsight.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import com.pluralsight.model.*;
import com.pluralsight.data.ReceiptFileManager;

public class OrderScreen extends HomeScreen {
    private static Scanner input = new Scanner(System.in);
    private static final String RESET = "\033[0m";
    private static final String RED = "\033[31m";
    private static final String GREEN = "\033[32m";

    public static void startNewOrder() {
        Order order = new Order();
        boolean editingOrder = true;

        String divider = "-----------------------------------------";

        while (editingOrder) {
            System.out.println("\n\n\n" + "🥪 Welcome to the Order Screen!\n");

            boolean addingItems = true;
            // Order item selection loop.
            while (addingItems) {
                printCurrentTime(); // ⏰ Show current time

                // Simple menu display with a divider.
                System.out.println(divider);
                System.out.println("       Order at Fresh Byte Deli        ");
                System.out.println(divider);
                System.out.println(GREEN + "[1]" + RESET + " Add Sandwich");
                System.out.println(GREEN + "[2]" + RESET + " Add Drink");
                System.out.println(GREEN + "[3]" + RESET + " Add Chips");
                System.out.println(GREEN + "[4]" + RESET + " Finish Order & Checkout");
                System.out.println(divider);
                System.out.print(" Enter your choice [1-4]: ");

                if (input.hasNextInt()) {
                    int choice = input.nextInt();
                    input.nextLine(); // Consume newline

                    switch (choice) {
                        case 1:
                            // Create and add a sandwich.
                            Sandwich sandwich = createSandwich();
                            order.getSandwiches().add(sandwich);
                            System.out.println(GREEN + "\n+ $" + String.format("%.2f", sandwich.getPrice())
                                    + " added for Sandwich." + RESET);
                            break;
                        case 2:
                            // Create and add a drink.
                            Drink drink = createDrink();
                            order.getDrinks().add(drink);
                            System.out.println(GREEN + "\n+ $" + String.format("%.2f", drink.getPrice())
                                    + " added for Drink." + RESET);
                            break;
                        case 3:
                            // Create and add chips using the static helper that validates input.
                            Chip chip = new Chip(1.50);
                            order.getChips().add(chip);
                            System.out.println(GREEN + "\n+ $" + String.format("%.2f", chip.getPrice())
                                    + " added for Chips." + RESET);
                            break;
                        case 4:
                            addingItems = false;
                            break;
                        default:
                            System.out.println(RED + "❌ Invalid option. Try again." + RESET);
                            break;
                    }
                } else {
                    input.nextLine(); // Consume invalid input
                    System.out.println(RED + "🚫 Invalid entry! Please enter a number." + RESET);
                }
            }

            // Display the final order summary.
            System.out.println("\n" + "🧾 Order Summary:");
            System.out.println(divider);
            System.out.println(order);
            System.out.println(divider);

            // Wait for confirmation (or to go back and edit).
            int confirmChoice = waitForConfirm();
            if (confirmChoice == 1) {
                // Save the transaction and receipt.
                ReceiptFileManager.saveTransaction(order);
                ReceiptFileManager.saveTransactionReceipt(order);
                System.out.println("✅ Thank you for ordering at Fresh Byte Deli!");
                editingOrder = false;
            } else if (confirmChoice == 2) {
                System.out.println("🔙 Returning to order editing...");
                // Optionally, you could clear the order or let the user modify it.
            }
        }
    }

    private static Sandwich createSandwich() {
        System.out.println("\n🥪 Creating Sandwich...");

        String bread = BreadType.getBreadType(input);
        String size = Sandwich.getValidSandwichSize();
        String meat = Meat.getMeatTypes(input);
        String cheese = Toppings.getCheeseTypes(input);
        boolean extraMeat = Meat.getExtraMeat(input);
        boolean extraCheese = Toppings.getExtraCheese(input);
        boolean isToasted = getBooleanInput("\nIs the sandwich toasted?");
        ArrayList<String> sauces = Sauces.getSauces(input);
        ArrayList<String> toppings = Toppings.getToppings(input);
        return new Sandwich(size, meat, cheese, extraMeat, extraCheese, bread, isToasted, sauces, toppings);
    }

    private static Drink createDrink() {
        boolean validDrink = false;
        String drinkSize = "";


        while (!validDrink) {
            drinkSize = getOptionalInput("\n Enter drink size (Small, Medium, Large): ").trim();
            if (Drink.isValidSize(drinkSize)) {
                validDrink = true;
            } else {
                System.out.println("❌ Invalid drink size. Please try again.");
            }
        }
        return new Drink(drinkSize);
    }

    private static String getOptionalInput(String prompt) {
        System.out.print(prompt);
        return input.nextLine().trim();
    }

    private static boolean getBooleanInput(String prompt) {
        while (true) {
            System.out.print(prompt + " (yes/no): ");
            String response = input.nextLine().trim().toLowerCase();
            if (response.isEmpty()) {
                return false;
            }
            if (response.equals("yes")) {
                return true;
            }
            if (response.equals("no")) {
                return false;
            }
            System.out.println("❌ Invalid response! Please enter 'yes' or 'no', or press Enter to skip.");
        }
    }

    private static int waitForConfirm() {
        while (true) {
            System.out.print("\nPress Enter (or 1) to confirm and continue, or press 2 to go back: ");
            System.out.flush();
            String response = input.nextLine().trim();
            if (response.isEmpty() || response.equals("1")) {
                return 1;
            } else if (response.equals("2")) {
                return 2;
            } else {
                System.out.println("❌ Invalid input! Please press Enter or 1 to confirm, or 2 to go back.");
            }
        }
    }

    private static void printCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy | hh:mm a");
        String formattedTime = now.format(formatter);
        System.out.println("\n" + "Time Now: " + formattedTime);
    }
}




