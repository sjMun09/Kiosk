package com.example.kiosk.service;

import com.example.kiosk.dto.CartStatus;
import com.example.kiosk.dto.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<MenuItem> items;
    private CartStatus cartStatus;

    private void updateCartStatus() {
        if (items.isEmpty()) cartStatus = CartStatus.EMPTY;
        else if (items.size() > 0) cartStatus = CartStatus.PARTIALLY_FILLED;
        else cartStatus = CartStatus.FULL;
    }

    public Cart() {
        this.items = new ArrayList<>();
        updateCartStatus();
    }

    public void addItem(MenuItem item) {
        items.add(item);
        updateCartStatus();
    }

    public void displayCart() {
        if (cartStatus == CartStatus.EMPTY) {
            System.out.println("장바구니가 비어 있습니다.");
            return;
        }
        for (MenuItem item : items) {
            System.out.println(item);
        }
    }

    public double getTotalPrice() {
        return items.stream().mapToDouble(MenuItem::getPrice).sum();
    }

    public void clear() {
        items.clear(); // 비워주고
        updateCartStatus();
    }

    public boolean isEmpty() {
        return cartStatus == CartStatus.EMPTY;
    }

    public CartStatus getCartStatus() {
        return cartStatus;
    }

}
