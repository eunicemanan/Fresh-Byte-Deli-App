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

    public static void showHomeScreen() {
        while (running) {
            printCurrentTime(); //  Show current time before menu
            printHomeMenu();

            System.out.print(" Enter your choice" + GREEN + " [ ENTER 1 OR 2]: " + RESET);

            if (input.hasNextInt()) {
                int choice = input.nextInt();
                input.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.println("\n" +  "      🧾  One moment...");
                        try {
                            Thread.sleep(2000); // pauses for 2 seconds
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            System.out.println(RED + "⚠ Sleep interrupted." + RESET);
                        }
                        OrderScreen.startNewOrder(); // Order screen line 16
                        String border = "═".repeat(50);
                        System.out.println(border);
                        // Here you can call your newOrder() method
                        break;

                    case 2:
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
                System.out.println(RED + invalidInput + "' is NOT a valid option. Please enter a number." + RESET);
            }
        }
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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy | hh:mm a");
        String formattedTime = now.format(formatter);
        System.out.println( "\n🕒  Time Now: " + formattedTime);
    }
}
