package com.chad.engine;

import com.chad.engine.gfx.Renderer;
import com.chad.engine.utils.Keyboard;
import com.chad.engine.utils.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Window {

    private static JFrame frame;
    private static BufferedImage frameImage;
    private static Graphics frameGraphics;
    private static Color backgroundColor;

    // window size
    public static int width, height;

    public static void init(String title, int width, int height) {
        Window.width = width;
        Window.height = height;

        frame = new JFrame(title);

        // set up some basic stuff
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);

        // center the frame
        frame.setLocationRelativeTo(null);

        // add listeners
        frame.addKeyListener(Keyboard.getListener());
        frame.addMouseListener(Mouse.getMouseListener());
        frame.addMouseWheelListener(Mouse.getMouseWheelListener());
        frame.addMouseMotionListener(Mouse.getMouseMotionListener());

        frame.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
//                Dimension d = frame.getSize();
//                Window.width = d.width;
//                Window.height = d.height;
//                frameImage = new BufferedImage(Window.width, Window.height, BufferedImage.TYPE_INT_RGB);
//                frameGraphics = frameImage.getGraphics();
//                Renderer.setGraphics(frameGraphics);
            }

            @Override
            public void componentMoved(ComponentEvent e) {}

            @Override
            public void componentShown(ComponentEvent e) {}

            @Override
            public void componentHidden(ComponentEvent e) {}
        });

        // set background color
        backgroundColor = Color.white;
        frame.setBackground(backgroundColor);

        Dimension d = Window.getSize();
        frameImage = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_RGB);
        frameGraphics = frameImage.getGraphics();
        Renderer.setGraphics(frameGraphics);
    }

    public static void draw() {
        frame.getContentPane().getGraphics().drawImage(frameImage, 0, 0, width, height, null);
        Renderer.setColor(backgroundColor);
        Renderer.fill();
    }

    public static void setSize(int width, int height) { frame.setSize(width, height); }
    public static void setMinSize(int width, int height) { frame.setMinimumSize(new Dimension(width, height)); }
    public static void setMaxSize(int width, int height) { frame.setMaximumSize(new Dimension(width, height)); }

    public static void setVisible(boolean visible) { frame.setVisible(visible); }
    public static void setBackgroundColor(Color color) { backgroundColor = color; }

    public static Graphics getGraphics() { return frameGraphics; }
    public static Dimension getSize() { return frame.getSize(); }
    public static BufferedImage getFrameImage() { return frameImage; }

    public static float getWidth() { return frame.getWidth(); }
    public static float getHeight() { return frame.getHeight(); }
}
