package com.pluralsight.model;

import java.util.ArrayList;

public class Drinks extends MenuItem {
    private String size;
    private String flavor;

    private static final ArrayList<String> drinkFlavors = new ArrayList<String>();

    static {
        drinkFlavors.add("Sprite");
        drinkFlavors.add("Coke");
        drinkFlavors.add("Coke Zero");
        drinkFlavors.add("Root Beer");
        drinkFlavors.add("Dr Pepper");
    }

    public Drinks(String name, String size, String flavor) {
        super(name, getPriceBySize(size));
        this.size = size;
        this.flavor = flavor;
    }

    private static double getPriceBySize(String size) {
        if (size.equalsIgnoreCase("small")) {
            return 2.00;
        } else if (size.equalsIgnoreCase("medium")) {
            return 2.50;
        } else if (size.equalsIgnoreCase("large")) {
            return 3.00;
        } else {
            return 0.0; // default price if size not recognized
        }
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
        setPrice(getPriceBySize(size));
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }
    public static ArrayList<String> getAvailableFlavors() {
        return drinkFlavors;
    }

    @Override
    public String toString() {
        return getName() + " (" + size + ", " + flavor + ") - $" + String.format("%.2f", getPrice());
    }
}
