package com.example.kiosk;

import com.example.kiosk.controller.Kiosk;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Kiosk kiosk = new Kiosk();
        kiosk.run();
    }
}
