package com.example.kiosk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 프로그램 순서 및 흐름 제어
 */
public class Kiosk {
    private Menu menu;
    private BufferedReader br;

    public Kiosk() {
        this.menu = new Menu();
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    public void run() throws IOException {
        boolean running = true;

        while (running) {
            // menu.initializeMenu();
            menu.outMenu();
            System.out.print("메뉴를 선택하세요: ");
            int choice;

            try {
                choice = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.\n");
                continue;
            }

            if (choice == 0) {
                System.out.println("프로그램을 종료합니다.");
                running = false;
                break;
            }

            if (choice < 1 || choice > menu.getMenuSize()) {
                System.out.println("잘못된 입력입니다. 다시 시도하세요.\n");
                continue;
            }

            handleOrder(choice);
        }
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
