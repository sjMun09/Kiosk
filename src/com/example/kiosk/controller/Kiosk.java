package com.example.kiosk.controller;

import com.example.kiosk.service.Cart;
import com.example.kiosk.dao.Menu;
import com.example.kiosk.dto.Discount;
import com.example.kiosk.dto.MenuItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 프로그램 순서 및 흐름 제어
 */
public class Kiosk {
    private Menu menu;
    private BufferedReader br;
    //private Cart cart;
    private Cart<MenuItem> cart; // 제네릭 타입 명시 해줌

    public Kiosk() {
        this.menu = new Menu();
        this.br = new BufferedReader(new InputStreamReader(System.in));
        this.cart = new Cart(); // // 제네릭 타입은 컴파일러가 추적 가능하다.
    }

    private void displayMainMenu() {
        System.out.println("[ MAIN MENU ]");
        System.out.println("1. Burgers");
        System.out.println("2. Drinks");
        System.out.println("3. Desserts");
        System.out.println("0. 종료      | 종료");
        if (!cart.isEmpty()) {
            System.out.println("\n[ ORDER MENU ]");
            System.out.println("4. Orders       | 장바구니를 확인 후 주문합니다.");
            System.out.println("5. Cancel       | 진행중인 주문을 취소합니다.");
        }
    }

    private void handleMenuSelection(int category) throws IOException {
        menu.outMenu();
        System.out.print("메뉴를 선택하세요: ");
        int choice;
        System.out.println(" ");
        try {
            choice = Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
            return;
        }
        if (choice == 0) {
            System.out.println("이전 메뉴로 돌아갑니다.");
            return;
        } else if (choice < 1 || choice > menu.getMenuSize()) {
            System.out.println("잘못된 입력입니다. 다시 시도하세요.");
            return;
        }

        MenuItem selectItem = menu.getMenuItem(choice - 1);

        System.out.println("선택한 메뉴: " + selectItem);
        System.out.print("위 메뉴를 장바구니에 추가하시겠습니까? \n1. 확인 \t  2. 취소 \n");
        System.out.println(" ");

        int filter;

        try {
            filter = Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다.");
            return;
        }

        if (filter == 1) {
            cart.addItem(selectItem); // Cart 수정 후, 동일 메뉴가 추가 시엔 수량이 증가한다.
            System.out.println(selectItem.getBurger() + "이 장바구니에 추가되었습니다");
            System.out.println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.");
        } else System.out.println("장바구니 추가가 취소되었습니다.\n");

    }

    public void run() throws IOException {
        boolean running = true;

        while (running) {
            // menu.initializeMenu();
            //menu.outMenu();
            // 원래는 out으로 보이는 것을 표현했었는데, display로 수정
            displayMainMenu();

            System.out.print("메뉴를 선택하세요: ");
            int choice;

            try {
                choice = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.\n");
                continue;
            }

            switch (choice) {
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    running = false;
                    break;
                case 1, 2, 3:
                    handleMenuSelection(choice);
                    break;
                case 4:
                    if (cart.isEmpty()) {
                        System.out.println("장바구니가 비어 있습니다.");
                    } else processOrder();
                case 5:
                    if (cart.isEmpty()) {
                        System.out.println("장바구니가 비어 있습니다.");
                    } else {
                        cart.clear();
                        System.out.println("장바구니를 비웠습니다.");
                    }
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 시도하세요.");
            }
        }
        // handleOrder(choice);
    }


    private void processOrder() throws IOException {
        System.out.println("아래와 같이 주문 하시겟습니까?\n");

        System.out.println("[ Orders ]");
        cart.displayCart(); // 제네릭 타입을 유지하게 됌
        System.out.println(" ");
        System.out.println("[ Total ]\nW " + cart.getTotalPrice() + "\n");
        System.out.print("1. 주문  \t  2. 메뉴판 \n");
        int choice;

        try {
            choice = Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다.\n");
            return;
        }

        if (choice == 1) {
            applyDiscount();
        } else {
            System.out.println("메뉴판으로 돌아갑니다.\n");
        }
    }

    private void applyDiscount() throws IOException {
        System.out.println("[ 할인 정보 ]");
        for (Discount discount : Discount.values()) {
            System.out.println((discount.ordinal() + 1) + ". " + discount);
        }

        System.out.print("할인 정보를 입력해주세요: ");
        int discountChoice;

        try {
            discountChoice = Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다.\n");
            return;
        }

        Discount userType = Discount.fromChoice(discountChoice);
        double totalPrice = cart.getTotalPrice();
        double discountedPrice = totalPrice * (1 - userType.getDiscountRate());

        System.out.printf("사용자 유형: %s\n", userType.getDescription());
        System.out.printf("할인 적용 후 금액은 W %.2f 입니다.\n", discountedPrice);
        cart.clear();
        System.out.println("주문이 완료되었습니다. 감사합니다!\n");
    }


//    private void initializeMenu() {
//        menu.outMenu();
//    }

    private void handleOrder(int choice) throws IOException {
        MenuItem selectedItem = menu.getMenuItem(choice - 1);
        System.out.println("주문하신 메뉴: " + selectedItem);
        System.out.print("주문하신게 맞을까요? (Y/N): ");
        String confirm = br.readLine().trim().toUpperCase();

        if (confirm.equals("N")) {
            System.out.println("다시 주문해주세요.\n");
            return;
        } else if (confirm.equals("Y")) {
            System.out.print("몇 개를 주문하시겠습니까? : ");
            int quantity;

            try {
                quantity = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                System.out.println("올바른 수량을 입력해주세요.\n");
                return;
            }

            if (quantity <= 0) {
                System.out.println("올바른 수량을 입력해주세요.\n");
                return;
            }

            double totalPrice = selectedItem.getPrice() * quantity;
            System.out.println("총 주문 금액은: W " + totalPrice + "입니다.\n");
        } else {
            System.out.println("잘못된 입력입니다. 다시 시도하세요.\n");
        }
    }
}
