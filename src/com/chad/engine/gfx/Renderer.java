package com.chad.engine.gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Renderer {

    private static Graphics graphics;

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
