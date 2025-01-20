package com.example.kiosk.dto;

/**
 * 마트에 있는 "장바구니" 느낌
 */
public class CartItem {
    private MenuItem menuItem; // 선반의 메뉴 항목
    private int quantity; // 해당 메뉴의 전체 수량

    public CartItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice(){
        return menuItem.getPrice() * quantity;
    }

    public String toString() {
        return menuItem + " * " + quantity + " = W " + getTotalPrice();
    }
}
