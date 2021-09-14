package com.chad.engine.ui;

import com.chad.engine.entity.*;
import com.chad.engine.utils.Mathf;
import com.chad.engine.utils.Mouse;
import com.chad.engine.Window;

import java.awt.Point;

public class UIElement extends Entity {

	public static final byte ON_MOUSE_ENTER = 20;
	public static final byte ON_MOUSE_EXIT = 21;
	public static final byte ON_MOUSE_CLICK = 22;

	// visibility and whether the mouse has entered
	private boolean mouseEntered;

	// anchor point for the element
	private final Point anchor;

	public UIElement() {
		super();

		mouseEntered = false;

		anchor = new Point();
	}

	public void centerX() {
		anchor.x = Window.width / 2;
//	    setX((Game.instance().getWindowSize().width - width) * 0.5f);
    }

    public void centerY() {
		anchor.y = Window.height / 2;
//        setY((Game.instance().getWindowSize().height - height) * 0.5f);
    }

	public void setAnchor(float x, float y) { anchor.x = (int)x; anchor.y = (int)y; }
	public void setAnchorX(float x) { anchor.x = (int)x; }
	public void setAnchorY(float y) { anchor.y = (int)y; }
	
	@Override
	public void update(float dt) {
		
		// do nothing if it's invisible
		if (!visible)
			return;
			
		// check if the mouse moved
		if (Mouse.moved()) {
				
			// check if the mouse is within the ui element
			boolean inRect = Mathf.pointWithinRect(Mouse.position(), getBounds());
			
			// if the current position is within the element but isn't set in the
			// mouse listener, set it
			if (!mouseEntered && inRect)
				invokeListeners(ON_MOUSE_ENTER);
			
			// if the current position is outside the element but is currently set,
			// reset it
			else if (mouseEntered && !inRect)
				invokeListeners(ON_MOUSE_EXIT);

			mouseEntered = inRect;
		}
		
		// check if the on click listener should be used
		if (mouseEntered && Mouse.clicked())
			invokeListeners(ON_MOUSE_CLICK);
	}

	/** sets the visibility of the element */
	@Override
	public void setVisible(boolean visible) { mouseEntered = (this.visible = visible) && mouseEntered; }

	@Override
	public boolean setAttribute(String attr, String value) throws Exception {
		switch (attr) {
			case "center-x":
				if (value.equals("true")) {
					centerX();
					return true;
				}
			case "center-y":
				if (value.equals("true")) {
					centerY();
					return true;
				}
			case "anchor-x":
				anchor.x = Integer.parseInt(value);
				return true;
			case "anchor-y":
				anchor.y = Integer.parseInt(value);
				return true;
			case "align":
				switch (value) {
					case "left":
						anchor.x = 0;
						return true;
					case "right":
						anchor.x = Window.width;
						return true;
					case "top":
						anchor.y = 0;
						return true;
					case "bottom":
						anchor.y = Window.height;
						return true;
				}
				break;
		}
		return super.setAttribute(attr, value);
	}

	@Override
	public float getRelativeX() { return super.getRelativeX() + anchor.x - width * 0.5f; }
	@Override
	public float getRelativeY() { return super.getRelativeY() + anchor.y - height * 0.5f; }

	/** is the mouse within the bounds of the element? */
	public final boolean mouseWithinBounds() {
		return mouseEntered;
	}

	public Listener addMouseHoverListener(Action action) { return addListener(ON_MOUSE_ENTER, action); }
	public Listener addMouseExitListener(Action action) { return addListener(ON_MOUSE_EXIT, action); }
	public Listener addOnClickListener(Action action) { return addListener(ON_MOUSE_CLICK, action); }
}
