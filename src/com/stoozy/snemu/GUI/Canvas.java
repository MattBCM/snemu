package com.stoozy.snemu.GUI;

import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.image.BufferedImage;

public class Canvas extends JComponent {
    private int width;
    private int height;
    private BufferedImage framebuffer;

    public Canvas(int w, int h) {
        width = w;
        height = h;
        framebuffer = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        fillFramebuffer(Color.BLACK);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(this.framebuffer, null, null);
    }

    public void fillFramebuffer(Color c) {
        int color = c.getRGB();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                framebuffer.setRGB(x, y, color);
            }
        }
    }

}
