package com.pluralsight.ui;

import java.util.ArrayList;
import java.util.Scanner;
import com.pluralsight.model.*;

public class OrderScreen extends HomeScreen {
    private static Scanner input = new Scanner(System.in);

    public static void startNewOrder() {
        Order order = new Order();
        boolean editingOrder = true;

        while (editingOrder) {
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
                            order.getDrinks().add(createDrink());
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

            // Display the order summary.
            System.out.println("\n🧾 Order Summary:\n" + order);

            // Wait for confirmation or a request to go back.
            int confirmChoice = waitForConfirm();
            if (confirmChoice == 1) {
                System.out.println("✅ Thank you for ordering at Fresh Byte Deli!");
                editingOrder = false; // Confirm and end editing.
            } else if (confirmChoice == 2) {
                System.out.println("🔙 Returning to order editing...");
                // Optionally, you can choose to either clear the order or allow further modifications.
                // In this example, we re-run the order screen.
                // For simplicity, let's continue the loop, which will display the order summary again.
            }
        }
    }

    private static Sandwich createSandwich() {
        System.out.println("\n🥪 Creating Sandwich...");

        String size = Sandwich.getValidSandwichSize();                // Validates size (4", 8", or 12")
        String meat = Meat.getMeatTypes(input);                         // Valid meat selection
        String cheese = Toppings.getCheeseTypes(input);                 // Valid cheese selection
        boolean extraMeat = Meat.getExtraMeat(input);                   // Extra meat? (yes/no)
        boolean extraCheese = Toppings.getExtraCheese(input);           // Extra cheese? (yes/no)
        String bread = BreadType.getBreadType(input);                   // Valid bread selection
        boolean isToasted = getBooleanInput("Is the sandwich toasted?");
        ArrayList<String> sauces = Sauces.getSauces(input);             // Returns validated list of sauces
        ArrayList<String> toppings = Toppings.getToppings(input);         // Returns validated list of toppings

        return new Sandwich(size, meat, cheese, extraMeat, extraCheese, bread, isToasted, sauces, toppings);
    }

    private static Drink createDrink() {
        System.out.println("\n🥤 Creating Drink...");
        Drink.displayFlavors(); // Displays available flavors

        boolean validDrink = false;
        String drinkSize = "";
        String drinkFlavor = "";

        while (!validDrink) {
            drinkFlavor = getOptionalInput("👉 Enter drink flavor: ").trim();

            if (Drink.isValidFlavor(drinkFlavor)) {
                drinkSize = getOptionalInput("👉 Enter drink size (Small, Medium, Large): ").trim();

                if (Drink.isValidSize(drinkSize)) {
                    validDrink = true;
                } else {
                    System.out.println("❌ Invalid drink size. Please try again.");
                }
            } else {
                System.out.println("❌ Invalid drink option. Please try again.");
            }
        }

        return new Drink("Drink", drinkSize, drinkFlavor);
    }

    private static Chip createChips() {
        System.out.println("\n🍟 Creating Chips...");
        Chip.getChipFlavors();
        String flavor = getOptionalInput("👉 Enter chip flavor: ");
        return new Chip(flavor);
    }

    private static String getOptionalInput(String prompt) {
        System.out.print(prompt);
        return input.nextLine().trim(); // Allows skipping choices.
    }

    private static boolean getBooleanInput(String prompt) {
        while (true) {
            System.out.print(prompt + " (yes/no): ");
            String response = input.nextLine().trim().toLowerCase();

            if (response.isEmpty())
                return false;
            if (response.equals("yes"))
                return true;
            if (response.equals("no"))
                return false;
            System.out.println("❌ Invalid response! Please enter 'yes' or 'no', or press Enter to skip.");
        }
    }

    /**
     * Waits for user confirmation.
     * Pressing Enter or "1" confirms the order.
     * Pressing "2" returns the user to the previous menu.
     *
     * @return 1 if confirmed; 2 if the user wants to go back.
     */
    private static int waitForConfirm() {
        while (true) {
            System.out.print("\nPress Enter (or 1) to confirm and continue, or press 2 to go back: ");
            System.out.flush(); // Ensure the text is displayed immediately.
            String response = input.nextLine().trim();
            if (response.isEmpty() || response.equals("1")) {
                return 1;  // Confirm and proceed with order.
            } else if (response.equals("2")) {
                return 2;  // Go back to order editing.
            } else {
                System.out.println("❌ Invalid input! Please press Enter or 1 to confirm, or 2 to go back.");
            }
        }
    }
}
