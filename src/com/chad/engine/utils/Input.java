package com.chad.engine.utils;

import java.awt.Point;

public class Input {
	
	// the maximum key code value that I care about
	private static final int MAX_CODE = 100;

	// array for all keys held and pressed
	private static boolean[] keysHeld = new boolean[MAX_CODE];
	private static boolean[] keysPressed = new boolean[MAX_CODE];
	
	// mouse stuff
	private static Point mousePos;
	private static boolean mouseMoved;
	private static boolean mousePressed;
	private static boolean mouseReleased;
	private static boolean mouseClicked;
	private static boolean mouseScrolled;
	private static int mouseScrollAmount;
	
	public Input() {}
	
	/** update keys by resetting every pressed key and the mouse clicked variable */
	public void update() {
		for (int i = 0; i < MAX_CODE; i++)
			keysPressed[i] = false;
		mouseReleased = false;
		mouseClicked = false;
		mouseMoved = false;
		mouseScrolled = false;
		mouseScrollAmount = 0;
	}
	
	/** called when a key is pressed */
	public void keyPressed(int code) {
		
		// make sure the code is within the acceptable range
		if (code < MAX_CODE) {
			
			// the key can only be pressed if it wasn't previously held
			if (!keysHeld[code])
				keysPressed[code] = true;
			keysHeld[code] = true;
		}
	}
	
	/** called when a key is released */
	public void keyReleased(int code) {
		
		// make sure key is within acceptable range
		if (code < MAX_CODE) {
			
			// both held and pressed are now false
			keysHeld[code] = false;
			keysPressed[code] = false;
		}
	}
	
	/** called when the mouse is moved */
	public void onMouseMove(Point mousePos) {
		mouseMoved = true;
		Input.mousePos = mousePos;
	}
	
	/** called when the mouse is pressed */
	public void onMousePress() {
		mousePressed = true;
		mouseReleased = false;
	}
	
	/** called when the mouse is clicked */
	public void onMouseClick() {
		mouseClicked = true;
		mouseReleased = false;
	}
	
	/** called when the mouse is released */
	public void onMouseRelease() {
		mousePressed = mouseClicked = false;
		mouseReleased = true;
	}
	
	/** called when the mouse is scrolled */
	public void onMouseScroll(int amount) {
		mouseScrollAmount += amount;
		mouseScrolled = true;
	}
	
	/** returns whether or not the key is held */
	public static boolean getKey(int code) {
		return keysHeld[code];
	}
	
	/** returns whether or not the key was pressed this frame */
	public static boolean getKeyDown(int code) {
		return keysPressed[code];
	}
	
	/** returns the mouse position */
	public static Point mousePos() { return mousePos; }
	
	/** returns whether or not the mouse was moved last frame */
	public static boolean mouseMoved() { return mouseMoved; }
	
	/** returns whether or not the mouse was clicked last frame */
	public static boolean mouseClicked() { return mouseClicked; }
	
	/** returns whether or not the mouse was released */
	public static boolean mouseReleased() { return mouseReleased; }
	
	/** returns whether or not the mouse is current pressed */
	public static boolean mousePressed() { return mousePressed; }
	
	/** returns whether or not the mouse was scrolled */
	public static boolean mouseScrolled() { return mouseScrolled; }
	
	/** returns the mouse's scroll amount. If the mouse wasn't scrolled, 0 is returned */
	public static int scrollAmount() { return mouseScrollAmount; }
	
}
