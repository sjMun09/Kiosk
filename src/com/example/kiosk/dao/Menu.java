package com.example.kiosk.dao;

import com.example.kiosk.dto.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * MenuItem 클래스를 관리하는 클래스
 */
public class Menu {
    private List<MenuItem> items;
    /*
    현재 로직에서는 장바구니에 추가된 항목들을 단순히 MenuItem 객체의 리스트로 관리되고 있는 중,
    각 항목의 수량을 관리하는 로직이 없다.
    즉, 동일한 메뉴를 여러 번 추가하더라도 장바구니에 새로운 객체로 추가되며 수량 개념은 포함되지 않은 상태다.
    -> 수량 개념 도입. List로 했을 시엔 장바구니에 동일한 메뉴를 추가하면 별도의 수량 증가 없이 중복된 MenuItem 객체가 추가됌.
    즉, 현재 장바구니와 메뉴는 서로 독립적으로 동작하며, "선반(메뉴)에서 장바구니로 물건을 옮기는" 구체적인 개념이 코드에 없다고 판단.
    -> 선반에 잇는 물건과 장바구니의 구분이 명확하지 않다는 말.
    해결) 수량 도입. 즉, field 의 items를 Map 형태로 변경.
     */

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
        System.out.println("0. 뒤로가기 | 종료하기");
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
