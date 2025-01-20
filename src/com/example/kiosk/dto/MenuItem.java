package com.example.kiosk.dto;

/**
 * 세부 메뉴 속성 -> 마트에 있는 "선반" 느낌
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

    public String getIntro() {
        return intro;
    }

    public void setBurger(String burger) {
        this.burger = burger;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Override
    public String toString() {
        return burger + "\t| W " + price + " |" + intro;
    }
}
