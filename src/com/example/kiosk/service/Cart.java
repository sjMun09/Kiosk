package com.example.kiosk.service;

import com.example.kiosk.dto.CartItem;
import com.example.kiosk.dto.CartStatus;
import com.example.kiosk.dto.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Cart {
    // private Map<T, Integer> items;
    // 다시 List로 수정.
    private List<CartItem> items;
    private CartStatus cartStatus;

    private void updateCartStatus() {
        if (items.isEmpty()) cartStatus = CartStatus.EMPTY;
        else  cartStatus = CartStatus.PARTIALLY_FILLED;
        // else cartStatus = CartStatus.FULL;
    }

    public Cart() {
        this.items = new ArrayList<>();
        updateCartStatus();
    }

   /* public Cart() {
        this.items = new HashMap<>();
        updateCartStatus();
    }*/


    /*public void addItem(T item) {
        items.add(item);
        updateCartStatus();
    }*/

    // 장바구니 추가
    public void addItem(MenuItem menuItem, int quantity) {
//        items.put(item, items.getOrDefault(item, 0) + 1); // 수량 ++
        for (CartItem cartItem : items) {
            if (cartItem.getMenuItem().equals(menuItem)) {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                updateCartStatus();
                return;
            }
        }
        items.add(new CartItem(menuItem, quantity)); // 새 항목 추가
        updateCartStatus();
    }

    //추가
    /*public void removeItemByCondition(String name) {
        items = items.stream()
                .filter(item -> !(item instanceof MenuItem) || !((MenuItem) item).getBurger().equals(name))
                .collect(Collectors.toList());
    }*/

    // 장바구니 감소
    public void removeItem(MenuItem menuItem, int quantity) {
//        if (items.containsKey(item)) {
//            int count = items.get(item);
//            if (count >1) items.put(item, count - 1);// 수량 감소
//            else items.remove(item); //수량 0일 경우 완전 삭제
//        }
        items.removeIf(cartItem->{
            if (cartItem.getMenuItem().equals(menuItem)) {
                int updateQuantity = cartItem.getQuantity() - quantity;
                if (updateQuantity > 0) {
                    cartItem.setQuantity(updateQuantity);// 수량 감소
                    return false;
                }
                return true;// 수량이 0이라면 삭젷 ㅐ버림
            }
            return false;
        });
        updateCartStatus();
    }

    // 전체 수량 표시
    public void displayCart() {
        if (cartStatus == CartStatus.EMPTY) {
            System.out.println("장바구니가 비어 있습니다.");
            return;
        } else items.forEach(System.out::println);
        /*for (MenuItem item : items) {
            System.out.println(item);
        }*/
//        items.forEach((item, count) -> System.out.println(item + "*" + count));
    }

    // 총 가격
    public double getTotalPrice() {
//        return items.stream()
//                .filter(item -> item instanceof MenuItem)
//                .mapToDouble(item -> ((MenuItem) item).getPrice())
//                .sum();
        /*return items.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();*/
        return items.stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }

   /* public double getTotalPrice() {
        return items.stream().mapToDouble(MenuItem::getPrice).sum();
    }*/


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

//
//    public Map<T, Integer> getItems() {
//        return new HashMap<>(items); // 방어적 복사 -> 공부
//    }

    public List<CartItem> getItem() {
        return new ArrayList<>(items);
    }
}
