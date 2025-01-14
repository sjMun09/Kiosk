package com.example.kiosk.dao;

import com.example.kiosk.dto.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * MenuItem 클래스를 관리하는 클래스
 */
public class Menu {
    private List<MenuItem> items;

    public Menu() {
        this.items = new ArrayList<>();
        this.initializeMenu();
    }

    public void initializeMenu() {
        items = new ArrayList<>();
        items.add(new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        items.add(new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        items.add(new MenuItem("CheeseBurger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        items.add(new MenuItem("HamBurger", 5.4, "비프 패티를 기반으로 야채가 들어간 기본 버거"));
    }

    public void outMenu() {
        if (items == null || items.isEmpty()) {
            System.out.println("메뉴가 존재하지 않습니다.");
            return;
        }
        System.out.println("[ SHAKESHACK MENU ]");


        for (int i = 0; i < items.size(); i++) {
            System.out.println(i + 1 + ". " + items.get(i));
        }
        System.out.println("0. 뒤로가기");
    }

    public MenuItem getMenuItem(int index) {
        return items.get(index);
    }

    public int getMenuSize() {
        return items.size();
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

}
