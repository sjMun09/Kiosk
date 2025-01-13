package com.example.kiosk;

/**
 * 세부 메뉴 속성
 */
public class MenuItem {
    private String burger;
    private double price;
    private String intro;

    public MenuItem(String shackBurger, double price, String intro) {
        this.burger = shackBurger;
        this.price = price;
        this.intro = intro;
    }

    public String getBurger() {
        return burger;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return burger + "\t| W " + price + " |" + intro;
    }
}
