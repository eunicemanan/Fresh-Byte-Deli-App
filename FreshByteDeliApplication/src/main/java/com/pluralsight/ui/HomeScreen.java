package com.pluralsight.ui;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HomeScreen {

    private static boolean running = true;
    private static Scanner input = new Scanner(System.in);

    // colors
    private static final String RESET = "\033[0m";
    private static final String RED = "\033[31m";
    private static final String GREEN = "\033[32m";
    private static final String YELLOW = "\033[33m";
    private static final String PURPLE = "\033[35m";

    public static void showHomeScreen() {
        while (running) {
            printCurrentTime(); // ⏰ Show current time before menu
            printHomeMenu();

            String indent = String.format("%7s", "");
            System.out.print(indent + "      👉 Enter your choice" + GREEN + " [ ENTER 1 OR 2]: " + RESET);

            if (input.hasNextInt()) {
                int choice = input.nextInt();
                input.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        OrderScreen.startNewOrder(); // place holder
                        String border = "═".repeat(50);
                        System.out.println("\n" + indent + "🧾 Starting a new order...");
                        System.out.println(indent + border);
                        try {
                            Thread.sleep(2000); // pauses for 2 seconds
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            System.out.println(RED + "⚠ Sleep interrupted." + RESET);
                        }
                        // Here you can call your newOrder() method
                        break;

                    case 2:
                        try {
                            System.out.println(String.format("\n%6s%s", "", RED + " ⚠  Hold tight! Powering down...⚠" + RESET));
                            System.out.println("\n" + indent + GREEN + "👋 Fresh Byte Deli signing off... See you soon!" + RESET);
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
                System.out.println(RED + "\n🚫 '" + invalidInput + "' is NOT a valid option. Please enter a number." + RESET);
            }
        }
    }

    private static void printHomeMenu() {
        String indent = String.format("%10s", ""); // 14 spaces
        String border = "═".repeat(41);

        System.out.println(indent + "╔" + border + "╗");
        System.out.println(indent + "║    🥪  Welcome to Fresh Byte Deli  💻    ");
        System.out.println(indent + "╠" + border + "╣");
        System.out.println(indent + "║" + GREEN + "[1]" + RESET + " 🛒 Start New Order");
        System.out.println(indent + "║" + GREEN + "[2]"  + RESET + " ❌ Exit Application");
        System.out.println(indent + "╚" + border + "╝");
    }

    private static void printCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy | hh:mm a");
        String formattedTime = now.format(formatter);
        System.out.println("\n(¯`·.¸¸.·´¯) 🕒  Time Now: " + formattedTime + " (¯`·.¸¸.·´¯)");
    }
}
