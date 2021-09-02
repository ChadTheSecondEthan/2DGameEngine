package com.chad.engine.utils;

import java.awt.Point;
import java.awt.event.*;

public class Mouse {
	
	// mouse stuff
	private static Point position;
	private static boolean moved;
	private static boolean pressed;
	private static boolean released;
	private static boolean clicked;
	private static boolean scrolled;
	private static int scrollAmount;
	
	/** update keys by resetting every pressed key and the mouse clicked variable */
	public static void update() {
		released = false;
		clicked = false;
		moved = false;
		scrolled = false;
		scrollAmount = 0;
	}
	
	/** called when the mouse is moved */
	private static void onMouseMove(Point position) {
		moved = true;
		Mouse.position = position;
	}
	
	/** called when the mouse is pressed */
	private static void onMousePress() {
		pressed = true;
		released = false;
	}
	
	/** called when the mouse is clicked */
	private static void onMouseClick() {
		clicked = true;
		released = false;
	}
	
	/** called when the mouse is released */
	private static void onMouseRelease() {
		pressed = clicked = false;
		released = true;
	}
	
	/** called when the mouse is scrolled */
	private static void onMouseScroll(int amount) {
		scrollAmount += amount;
		scrolled = true;
	}
	
	/** returns the mouse position */
	public static Point position() { return position; }
	
	/** returns whether or not the mouse was moved last frame */
	public static boolean moved() { return moved; }
	
	/** returns whether or not the mouse was clicked last frame */
	public static boolean clicked() { return clicked; }
	
	/** returns whether or not the mouse was released */
	public static boolean released() { return released; }
	
	/** returns whether or not the mouse is current pressed */
	public static boolean pressed() { return pressed; }
	
	/** returns whether or not the mouse was scrolled */
	public static boolean scrolled() { return scrolled; }
	
	/** returns the mouse's scroll amount. If the mouse wasn't scrolled, 0 is returned */
	public static int scrollAmount() { return scrollAmount; }

	public static MouseListener getMouseListener() {
		return new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Mouse.onMouseClick();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Mouse.onMousePress();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				Mouse.onMouseRelease();
			}

			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		};
	}

	public static MouseMotionListener getMouseMotionListener() {
		return new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {}

			@Override
			public void mouseMoved(MouseEvent e) {
				Mouse.onMouseMove(e.getPoint());
			}
		};
	}

	public static MouseWheelListener getMouseWheelListener() {
		return e -> Mouse.onMouseScroll(e.getScrollAmount() * e.getWheelRotation());
	}
}
