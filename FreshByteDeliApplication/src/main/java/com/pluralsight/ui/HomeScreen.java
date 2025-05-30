package com.pluralsight.ui; // Ensure this matches your package

import com.pluralsight.model.Order; // Import Order class if you display final order details
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class HomeScreen {

    private static boolean running = true;
    private static Scanner input = new Scanner(System.in); // Keep scanner static for simplicity in this structure

    // colors
    private static final String RESET = "\033[0m";
    private static final String RED = "\033[31m";
    private static final String GREEN = "\033[32m";
    private static final String YELLOW = "\033[33m"; // Added for consistency

    public static void showHomeScreen() {
        // Create an instance of OrderScreen. This instance will manage the current order.
        OrderScreen orderScreen = new OrderScreen(input);

        while (running) {
            printCurrentTime(); // Show current time before menu
            printHomeMenu();

            System.out.print(" Enter your choice" + GREEN + " [ ENTER 1 OR 2]: " + RESET);

            if (input.hasNextInt()) {
                int choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1: // Start New Order
                        System.out.println("\n" + "      🧾  One moment...");
                        try {
                            Thread.sleep(1000); // pauses for 1.5 seconds
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            System.out.println(RED + "⚠ Sleep interrupted." + RESET);
                        }

                        // Call the OrderScreen's main method and capture its return.
                        // OrderScreen will return an int indicating user's final action:
                        int orderAction = orderScreen.displayOrderMenu();

                        if (orderAction == 0) { // User chose to Checkout
                            System.out.println("\n" + "═".repeat(50));
                            System.out.println(YELLOW + "🧾 Finalizing your order..." + RESET);
                            Order finalOrder = orderScreen.getCurrentOrder(); // Get the completed order
                            System.out.println(finalOrder.toString()); // Print the full receipt
                            System.out.println("═".repeat(50));
                            System.out.println(GREEN + "Order completed! Thank you for your business." + RESET);

                            // After checkout, create a NEW OrderScreen instance for the next potential order.
                            // This effectively "resets" the order.
                            orderScreen = new OrderScreen(input);

                        } else if (orderAction == 1) { // User chose to Go Back to Main Menu
                            System.out.println(YELLOW + "\nReturning to Home Screen." + RESET);
                            // No specific action needed here, the while(running) loop in HomeScreen will naturally
                            // re-display the home menu.
                        }
                        break;

                    case 2: // Exit Application
                        try {
                            System.out.println(String.format("\n%6s%s", "", RED + " ⚠  Hold tight! Powering down...⚠" + RESET));
                            System.out.println("\n" + GREEN + "👋 Fresh Byte Deli signing off... See you soon!" + RESET);
                            Thread.sleep(2000); // pauses for 2 seconds
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            System.out.println(RED + "⚠ Sleep interrupted." + RESET);
                        }
                        running = false;  // exit the loop to end the program
                        break;

                    default:
                        System.out.println(RED + "\n❌ Invalid option. Please try again." + RESET);
                        break;
                }
            } else {
                String invalidInput = input.nextLine(); // consume the invalid input
                System.out.println(RED + "'" + invalidInput + "' is NOT a valid option. Please enter a number." + RESET);
            }
        }
        input.close(); // Close the scanner when the application exits
    }

    private static void printHomeMenu() {
        String divider = "-----------------------------------------";

        System.out.println(divider);
        System.out.println("   🥪  Welcome to Fresh Byte Deli  💻");
        System.out.println(divider);
        System.out.println(GREEN + "[1]" + RESET + " 🛒 Start New Order");
        System.out.println(GREEN + "[2]" + RESET + " ❌ Exit Application");
        System.out.println(divider);
    }

    private static void printCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy | hh:mm a"); // Corrected to yyyy
        String formattedTime = now.format(formatter);
        System.out.println("\n🕒  Time Now: " + formattedTime);
    }
}
