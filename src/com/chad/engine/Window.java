package com.chad.engine;

import com.chad.engine.gfx.Renderer;
import com.chad.engine.utils.Keyboard;
import com.chad.engine.utils.Mouse;
import com.chad.engine.utils.Time;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.nio.Buffer;

public class Window {

    private static JFrame frame;
    private static BufferedImage frameImage;
    private static Graphics frameGraphics;

    // window size
    public static int width, height;

    // true if window is focused
    public static boolean focused;

    // frame/tick tracking
    private static int frames, fps;

    public static void init(String title, int width, int height) {
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
                Dimension d = frame.getSize();
                Window.width = d.width;
                Window.height = d.height;
                frameImage = new BufferedImage(Window.width, Window.height, BufferedImage.TYPE_INT_RGB);
                frameGraphics = frameImage.getGraphics();
                Renderer.setGraphics(frameGraphics);
            }

            @Override
            public void componentMoved(ComponentEvent e) {}

            @Override
            public void componentShown(ComponentEvent e) {}

            @Override
            public void componentHidden(ComponentEvent e) {}
        });

        // set background color
        frame.setBackground(Color.white);

        Dimension d = Window.getSize();
        frameImage = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_RGB);
        frameGraphics = frameImage.getGraphics();
        Renderer.setGraphics(frameGraphics);
    }

    public static void draw() { frameGraphics.drawImage(frameImage, 0, 0, width, height, null); }

    public static void setSize(int width, int height) { frame.setSize(width, height); }
    public static void setMinSize(int width, int height) { frame.setMinimumSize(new Dimension(width, height)); }
    public static void setMaxSize(int width, int height) { frame.setMaximumSize(new Dimension(width, height)); }

    public static void setFPS(int fps) { Window.fps = fps; }
    public static void setVisible(boolean visible) { frame.setVisible(visible); }

    public static Graphics getGraphics() { return frameGraphics; }
    public static Dimension getSize() { return frame.getSize(); }
    public static BufferedImage getFrameImage() { return frameImage; }

    // creates a window with the specified width/height
//    public static void init(String title, int width, int height) {
//        Window.width = width;
//        Window.height = height;
//
//        Window.lastSecond = Time.now();
//        Window.lastFrame = Time.now();
//
//        // create graphics device compatible buffered image
//        GraphicsConfiguration graphicsConfiguration = GraphicsEnvironment.getLocalGraphicsEnvironment()
//                .getDefaultScreenDevice()
//                .getDefaultConfiguration();
//        Window.image = graphicsConfiguration.createCompatibleImage(
//                Renderer.WIDTH, Renderer.HEIGHT, Transparency.OPAQUE);
//        Window.buffer = ((DataBufferInt) Window.image.getRaster().getDataBuffer()).getData();
//
//        // generate palette
//        int[] rendererPalette = Renderer.generatePalette();
//        Window.paletteImage = graphicsConfiguration.createCompatibleImage(256, 1);
//        Window.palette = ((DataBufferInt) Window.paletteImage.getRaster().getDataBuffer()).getData();
//
//        for (int i = 0; i < palette.length; i++) {
//            Window.paletteImage.setRGB(i, 0, rendererPalette[i]);
//        }
//
//        // open window
//        Window.frame = new JFrame(title);
//        Window.frame.getContentPane().setPreferredSize(new Dimension(width, height));
//        Window.frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
//        Window.frame.setResizable(true);
//        Window.frame.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                super.windowClosing(e);
//                Window.close = true;
//            }
//
//            @Override
//            public void windowGainedFocus(WindowEvent e) {
//                super.windowGainedFocus(e);
//                Window.focused = true;
//            }
//
//            @Override
//            public void windowLostFocus(WindowEvent e) {
//                super.windowLostFocus(e);
//                Window.focused = false;
//            }
//        });
//        Window.frame.addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//                super.componentResized(e);
//                Window.width = Window.canvas.getWidth();
//                Window.height = Window.canvas.getHeight();
//            }
//        });
//        Window.frame.setIgnoreRepaint(true);
//
//        Window.canvas = new Canvas(graphicsConfiguration);
//        Window.canvas.setPreferredSize(new Dimension(width, height));
//        Window.canvas.setSize(width, height);
//        Window.canvas.addKeyListener(Keyboard.getListener());
//        Window.canvas.setIgnoreRepaint(true);
//        Window.frame.add(canvas);
//        Window.frame.pack();
//        Window.frame.setLocationRelativeTo(null);
//
//        Window.frame.setVisible(true);
//        Window.canvas.setVisible(true);
//
//        Window.canvas.createBufferStrategy(2);
//        Window.bufferStrategy = Window.canvas.getBufferStrategy();
//    }
}
