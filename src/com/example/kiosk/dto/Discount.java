package com.example.kiosk.dto;

/**
 * 사용자 유형 및 할인율을 정의한 Enum
 */
public enum Discount {
    VETERAN("국가유공자", 0.10),
    SOLDIER("군인", 0.05),
    STUDENT("학생", 0.03),
    REGULAR("일반", 0.0);

    private final String description;
    private final double discountRate;

    Discount(String description, double discountRate) {
        this.description = description;
        this.discountRate = discountRate;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public String getDescription() {
        return description;
    }

    public static Discount fromChoice(int choice) {
        return switch (choice) {
            case 1 -> VETERAN;
            case 2 -> SOLDIER;
            case 3 -> STUDENT;
            default -> REGULAR;
        };
    }

    @Override
    public String toString() {
        return String.format("%s (할인율: %.0f%%)", description, discountRate * 100);
    }
}
