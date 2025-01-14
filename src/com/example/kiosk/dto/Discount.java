package com.example.kiosk.dto;

public enum Discount {
    VETERAN(0.10), SOLDIER(0.05), STUDENT(0.03), REGULAR(0.0);

    private final double rate;

    Discount(double rate) {
        this.rate = rate;
    }

    public static double getDiscountRate(int choice) {
        return switch (choice) {
            case 1 -> VETERAN.rate;
            case 2 -> SOLDIER.rate;
            case 3 -> STUDENT.rate;
            case 4 -> REGULAR.rate;
            default -> 0.0;
        };
    }
}
