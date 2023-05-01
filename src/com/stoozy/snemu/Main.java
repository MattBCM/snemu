package com.stoozy.snemu;

import java.awt.*;
import javax.swing.JFrame;

public class Main {
    private static int w = 500;
    private static int h = 480;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Canvas canvas = new Canvas(w, h);

        frame.setSize(w, h);
        frame.add(canvas);
        frame.setTitle("SNEMU");
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
