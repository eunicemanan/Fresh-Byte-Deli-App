package com.pluralsight.model;

import java.util.ArrayList;

public class BLT extends Sandwich {

    public BLT() {
        // Call the parent Sandwich constructor with:
        // bread, size, meat, cheese,
        // extraMeat, extraCheese, extraVegetables, isToasted,
        // sauces (empty list), toppings (empty list), extraSauce.
        super("white", "8", "Bacon", "Cheddar",
                false,    // extraMeat
                false,    // extraCheese
                false,    // extraVegetables
                true,     // isToasted
                new ArrayList<>(),  // sauces (initially empty)
                new ArrayList<>(),  // toppings (initially empty)
                false); // extraSauce

        // Create BLT-specific sauces and toppings.
        ArrayList<String> bltSauces = new ArrayList<>();
        bltSauces.add("Ranch");

        ArrayList<String> bltToppings = new ArrayList<>();
        bltToppings.add("Lettuce");
        bltToppings.add("Tomato");

        // Use the inherited setter methods from Sandwich to update the lists.
        setSauces(bltSauces);
        setToppings(bltToppings);
    }
}


