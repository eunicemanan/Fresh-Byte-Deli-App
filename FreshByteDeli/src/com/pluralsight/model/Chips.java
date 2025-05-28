package com.pluralsight.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Chips extends MenuItem {

    // Constants for chip flavors
    private static final String original = "Original";
    private static final String saltAndVinegar = "Salt and Vinegar";
    private static final String bbq = "BBQ";

    // List of available flavors, initialized using Arrays.asList
    private static final ArrayList<String> chipFlavors = new ArrayList<String>(Arrays.asList(original,saltAndVinegar,bbq));

    private String chipFlavor;

    // Constructor
    public Chips(String flavor) {
        super(flavor, 1.50);
        this.chipFlavor = flavor;
    }

    // Getter and setter
    public String getChipFlavor() {
        return chipFlavor;
    }

    public void setChipFlavor(String chipFlavor) {
        this.chipFlavor = chipFlavor;
        setName(chipFlavor); // update name in MenuItem
    }

    // Method to access the available chip flavors
    public static ArrayList<String> getAvailableFlavors() {
        return chipFlavors;
    }

    // toString method
    @Override
    public String toString() {
        return chipFlavor + " Chips - $" + String.format("%.2f", getPrice());
    }
}
