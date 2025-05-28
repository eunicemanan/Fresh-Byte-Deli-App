package com.pluralsight.model;

/**
 * Enum representing bread types in the deli POS system.
 */
public enum BreadType {
    WHITE, WHEAT, RYE, SOURDOUGH, MULTIGRAIN;


    /**
     * Converts a string input to a BreadType enum (case-insensitive).
     */
    public static BreadType fromString(String breadTypeStr) {
        if (breadTypeStr == null) return null;
        for (BreadType type : values()) {
            if (type.name().equalsIgnoreCase(breadTypeStr)) {
                return type;
            }
        }
        return null; // Handles invalid entries safely
    }
    /**
     * Formats the enum name for display.
     * Example: WHITE → "White"
     */
    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
