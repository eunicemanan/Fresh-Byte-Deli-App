package com.pluralsight.model;

import java.util.ArrayList;

public class PhillyCheeseSteak extends Sandwich {

    public PhillyCheeseSteak() {
        super("white", "8", "Steak", "American Cheese",
                false,    // extraMeat
                false,    // extraCheese
                false,    // extraVegetables
                true,     // isToasted
                new ArrayList<>(),  // sauces (initially empty)
                new ArrayList<>(),  // toppings (initially empty)
                false);   // extraSauce

        // Create Philly Cheese Steak-specific sauce and topping lists.
        ArrayList<String> phillySauces = new ArrayList<>();
        phillySauces.add("Mayo");

        ArrayList<String> phillyToppings = new ArrayList<>();
        phillyToppings.add("Peppers");

        // Use the inherited setter methods from Sandwich to update the sauces and toppings.
        setSauces(phillySauces);
        setToppings(phillyToppings);
    }
}
