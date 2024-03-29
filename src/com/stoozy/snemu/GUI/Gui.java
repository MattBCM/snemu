package com.stoozy.snemu.GUI;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class Gui {

    private static int width;
    private static int height;

    Canvas canvas;

    public Gui(int w, int h) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        } catch (Exception ignored) {
        }

        width = w;
        height = h;

    }

    public void display() {
        JFrame frame = new JFrame();
        canvas = new Canvas(width, height);
        Menu menu = new Menu();

        frame.setSize(width, height);
        frame.setJMenuBar(menu);
        frame.add(canvas);
        frame.setTitle("SNEMU");
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Graphics getGraphics() {
        return canvas.framebuffer.getGraphics();
    }
}
