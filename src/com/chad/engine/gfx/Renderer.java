package com.chad.engine.gfx;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class Renderer {
    public static final int[] DITHER = new int[]{0, 8, 2, 10, 12, 4, 14, 6, 3, 11, 1, 9, 15, 7, 13, 5};

    // renderer flags
    public static final int FLIP_NONE = 0x00, FLIP_X = 0x01, FLIP_Y = 0x02, FLIP_XY = FLIP_X | FLIP_Y;

    // target width/height
    public static final int WIDTH = 256, HEIGHT = 144;

    // screen pixels, each entry in [0..216) referring to palette entry
    // palette is determined by Window
    public static int[] pixels = new int[WIDTH * HEIGHT];

    private static Graphics graphics;

    // generates color palette, 24-bpp RGB
    public static int[] generatePalette() {
        int[] result = new int[256];

        int i = 0;
        for (int r = 0; r < 6; r++) {
            for (int g = 0; g < 6; g++) {
                for (int b = 0; b < 6; b++) {
                    int rr = (r * 255) / 5,
                        gg = (g * 255) / 5,
                        bb = (b * 255) / 5,
                        m = (rr * 30 + gg * 59 + bb * 11) / 100;

                    result[i++] = ((((rr + m) / 2) * 230 / 255 + 10) << 16) |
                        ((((gg + m) / 2) * 230 / 255 + 10) << 8) |
                        (((bb + m) / 2) * 230 / 255 + 10);
                }
            }
        }

        return result;
    }

    public static void fill(int x, int y, int w, int h) { graphics.fillRect(x, y, w, h); }
    public static void fill(float x, float y, float w, float h) { graphics.fillRect((int)x, (int)y, (int)w, (int)h); }
    public static void fill() { graphics.fillRect(0, 0, com.chad.engine.Window.width, com.chad.engine.Window.height);}

    public static void draw(BufferedImage image, int x, int y, int width, int height) { graphics.drawImage(image, x, y, width, height, null); }
    public static void draw(BufferedImage image, int x, int y) { graphics.drawImage(image, x, y, null); }
    public static void draw(BufferedImage image, float x, float y, float width, float height) { graphics.drawImage(image, (int)x, (int)y, (int)width, (int)height, null); }
    public static void draw(BufferedImage image, float x, float y) { graphics.drawImage(image, (int)x, (int)y, null); }

    public static void drawString(String s, int x, int y) { graphics.drawString(s, x, y); }
    public static void drawString(String s, float x, float y) { graphics.drawString(s, (int)x, (int)y); }

    public static void setFont(Font font) { graphics.setFont(font); }
    public static void setColor(java.awt.Color color) { graphics.setColor(color); }
    public static void setGraphics(Graphics graphics) { Renderer.graphics = graphics; }

    public static Font getFont() { return graphics.getFont(); }
    public static FontMetrics getFontMetrics() { return graphics.getFontMetrics(); }
}
