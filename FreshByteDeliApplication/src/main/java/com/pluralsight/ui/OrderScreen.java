package com.pluralsight.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.pluralsight.model.*;
import com.pluralsight.data.ReceiptFileManager;

public class OrderScreen {

    private final Scanner scanner;
    private Order currentOrder;

    private static final String RESET = "\033[0m";
    private static final String RED = "\033[31m";
    private static final String GREEN = "\033[32m";
    private static final String divider = "-----------------------------------------";

    public OrderScreen(Scanner scanner) {
        this.scanner = scanner;
        this.currentOrder = new Order();
    }

    public int displayOrderMenu() {
        boolean continueOrdering = true;
        int actionChosen = -1;

        System.out.println("\n\n\n" + "🥪 Welcome to the Order Screen!\n");

        while (continueOrdering) {
            printCurrentTime();

            // Updated printed menu to match our switch-case (six options)
            System.out.println(divider);
            System.out.println("       Order at Fresh Byte Deli        ");
            System.out.println(divider);
            System.out.println(GREEN + "[1]" + RESET + " Add Custom Sandwich");
            System.out.println(GREEN + "[2]" + RESET + " Add Signature Sandwich");
            System.out.println(GREEN + "[3]" + RESET + " Add Drink");
            System.out.println(GREEN + "[4]" + RESET + " Add Chips");
            System.out.println(GREEN + "[5]" + RESET + " Finish Order & Checkout");
            System.out.println(RED + "[6]" + RESET + " Go Back To Main Menu");
            System.out.println(divider);
            System.out.print(" Enter your choice [1-6]: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1: // Add Custom Sandwich
                        Sandwich customSandwich = createSandwich();
                        currentOrder.getSandwiches().add(customSandwich);
                        System.out.println(GREEN + "\n+ $" + String.format("%.2f", customSandwich.getPrice())
                                + " added for Sandwich." + RESET);
                        break;
                    case 2: // Add Signature Sandwich
                        sigSandwichScreen();
                        break;
                    case 3: // Add Drink
                        Drink drink = createDrink();
                        currentOrder.getDrinks().add(drink);
                        System.out.println(GREEN + "\n+ $" + String.format("%.2f", drink.getPrice())
                                + " added for Drink." + RESET);
                        break;
                    case 4: // Add Chips
                        Chip chip = new Chip(1.50);
                        currentOrder.getChips().add(chip);
                        System.out.println(GREEN + "\n+ $" + String.format("%.2f", chip.getPrice())
                                + " added for Chips." + RESET);
                        break;
                    case 5: // Finish Order & Checkout
                        System.out.println("\n" + "🧾 Order Summary:");
                        System.out.println(divider);
                        System.out.println(currentOrder);
                        System.out.println(divider);

                        int confirmChoice = waitForConfirm();
                        if (confirmChoice == 1) {
                            ReceiptFileManager.saveTransaction(currentOrder);
                            ReceiptFileManager.saveTransactionReceipt(currentOrder);
                            System.out.println("✅ Thank you for ordering at Fresh Byte Deli!");
                            actionChosen = 0;
                            continueOrdering = false;
                        } else if (confirmChoice == 2) {
                            System.out.println("🔙 Returning to order editing...");
                        }
                        break;
                    case 6: // Go Back To Main Menu
                        actionChosen = 1;
                        continueOrdering = false;
                        break;
                    default:
                        System.out.println(RED + "❌ Invalid option. Please try again." + RESET);
                        break;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println(RED + "🚫 Invalid entry! Please enter a number." + RESET);
            }
        }
        return actionChosen;
    }

    private Sandwich createSandwich() {
        System.out.println("\n🥪 Creating Sandwich...");

        String bread = BreadType.getBreadType(this.scanner);
        String size = Sandwich.getValidSandwichSize();
        String meat = Meat.getMeatTypes(this.scanner);
        String cheese = Toppings.getCheeseTypes(this.scanner);
        boolean extraMeat = Meat.getExtraMeat(this.scanner);
        boolean extraCheese = Toppings.getExtraCheese(this.scanner);
        boolean isToasted = getBooleanInput("\nIs the sandwich toasted?");
        ArrayList<String> sauces = Sauces.getSauces(this.scanner);
        ArrayList<String> toppings = Toppings.getToppings(this.scanner);
        boolean extraVegetables = Toppings.getExtraVegetables(this.scanner);
        boolean extraSauce = Sauces.getExtraSauce(this.scanner);

        // Use the 11-argument constructor in Sandwich.
        Sandwich newSandwich = new Sandwich(bread, size, meat, cheese, extraMeat, extraCheese, extraVegetables, isToasted, sauces, toppings, extraSauce);
        return newSandwich;
    }

    private void sigSandwichScreen() {
        System.out.println("---------------------------");
        System.out.println("Select a signature sandwich");
        System.out.println(GREEN + "[1]" + RESET + " BLT");
        System.out.println(GREEN + "[2]" + RESET + " Philly Cheese Steak");
        System.out.println(RED + "[0]" + RESET + " Go Back");
        System.out.print("Enter your choice here: ");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (choice == 1) {
                BLT blt = new BLT();
                customize(blt);
            } else if (choice == 2) {
                PhillyCheeseSteak philly = new PhillyCheeseSteak();
                customize(philly);
            } else if (choice == 0) {
                // Simply return to go back to the previous menu.
                return;
            } else {
                System.out.println(RED + "Input out of range. Please try again." + RESET);
                sigSandwichScreen(); // Recursively call to return here.
            }
        } catch (InputMismatchException e) {
            System.out.println(RED + "Invalid input! Please enter a valid number." + RESET);
            scanner.nextLine(); // Clear the invalid input
            sigSandwichScreen(); // Return to this screen if user input is wrong.
        }
    }


    // Helper method to add a signature sandwich to the order and print confirmation
    private void customize(Sandwich sandwich) {
        currentOrder.getSandwiches().add(sandwich);
        System.out.println(GREEN + "\n+ $" + String.format("%.2f", sandwich.getPrice())
                + " added for Signature Sandwich." + RESET);
    }

    private Drink createDrink() {
        boolean validDrink = false;
        String drinkSize = "";
        while (!validDrink) {
            System.out.print("Enter drink size (Small, Medium, Large): ");
            drinkSize = scanner.nextLine().trim();
            if (Drink.isValidSize(drinkSize)) {
                validDrink = true;
            } else {
                System.out.println("❌ Invalid drink size. Please try again.");
            }
        }
        return new Drink(drinkSize);
    }

    private boolean getBooleanInput(String prompt) {
        while (true) {
            System.out.print(prompt + " (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
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

    private int waitForConfirm() {
        while (true) {
            System.out.print("\nPress Enter (or 1) to confirm and continue, or press 2 to go back: ");
            System.out.flush();
            String response = scanner.nextLine().trim();
            if (response.isEmpty() || response.equals("1")) {
                return 1;
            } else if (response.equals("2")) {
                return 2;
            } else {
                System.out.println("❌ Invalid input! Please press Enter or 1 to confirm, or 2 to go back.");
            }
        }
    }

    private void printCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd,yyyy | hh:mm a");
        String formattedTime = now.format(formatter);
        System.out.println("\n" + "🕒  Time Now: " + formattedTime);
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }
}
