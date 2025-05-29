package com.pluralsight.model;

import com.pluralsight.model.menu.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;

public class Chips extends MenuItem {

    // List of valid chip flavors shared by all Chips instances
    private static final ArrayList<String> chipFlavors = new ArrayList<>(
            Arrays.asList("Original","Salt and Vinegar","BBQ"));

    // Instance field to hold the chosen chip flavor for this object
    private String chipFlavor;

    public Chips(String flavor) {
        super(flavor, 1.50); // Set inherited name and fixed price of $1.50
        if (chipFlavors.contains(flavor)) {
            this.chipFlavor = flavor;
        } else {
            // Prevent invalid flavors by throwing an exception
            throw new IllegalArgumentException("Invalid chip flavor: " + flavor);
        }
    }
    public String getChipFlavor() {
        return chipFlavor;

    }

    public void setChipFlavor(String chipFlavor) {
        if (chipFlavors.contains(chipFlavor)) {
            this.chipFlavor = chipFlavor;
            name(chipFlavor); // Keep MenuItem.name in sync
        } else {
            throw new IllegalArgumentException("Invalid chip flavor: " + chipFlavor);
        }
    }

    public static ArrayList<String> getAvailableFlavors() {
        return chipFlavors;
    }

     /*Returns a readable description of this chip order,
     including flavor and price.
     @return formatted string with flavor and price*/

    @Override
    public String toString() {
        return chipFlavor + " Chips - $" + String.format("%.2f", getPrice());
    }
}
