package com.pluralsight.data;

import com.pluralsight.model.Order;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptFileManager {

    // Master CSV file that will store all transactions
    private static final String masterFile = "data/Transactions.txt";

    public static void saveTransaction(Order order) {
        String timestamp = getCurrentTimeStamp();
        String transactionID = "TX_" + timestamp;

        // Create a summary for each order category. Replace any newline characters.
        String sandwiches = order.getSandwiches().toString().replace("\n", " | ");
        String drinks = order.getDrinks().toString().replace("\n", " | ");
        String chips = order.getChips().toString().replace("\n", " | ");
        double total = order.getTotal();

        String csvLine = String.format("%s,%s,\"%s\",\"%s\",\"%s\",%.2f",
                transactionID,
                timestamp,
                sandwiches,
                drinks,
                chips,
                total);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(masterFile, true))) {
            writer.write(csvLine);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to " + masterFile + ": " + e.getMessage());
        }
    }

    /**
     * Saves the current order as a separate receipt file.
     * The file name is based on a unique transaction ID (timestamp).
     * The receipt file is saved in the data directory as Transaction_<ID>.csv.*/

    public static void saveTransactionReceipt(Order order) {
        String timestamp = getCurrentTimeStamp();
        String transactionID = "TX_" + timestamp;
        String fileName = "data/Transaction_" + transactionID + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Write a header line.
            writer.write("Transaction ID,Date,Order Details,Total");
            writer.newLine();

            // Use order.toString() for full details; replace newlines with a delimiter so it fits on one CSV line.
            String orderDetails = order.toString().replace("\n", " | ");
            String line = String.format("%s,%s,\"%s\",%.2f",
                    transactionID, timestamp, orderDetails, order.getTotal());
            writer.write(line);
            writer.newLine();
            System.out.println("\nReceipt saved to: " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing the transaction receipt: " + e.getMessage());
        }
    }

    private static String getCurrentTimeStamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        return LocalDateTime.now().format(formatter);
    }
}
