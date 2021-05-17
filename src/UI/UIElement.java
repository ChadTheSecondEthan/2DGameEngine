package UI;

import java.awt.Point;
import java.awt.Rectangle;

import Entity.Action;
import Entity.Entity;
import GameState.GameState;
import Utils.Input;
import Utils.Mathf;

public abstract class UIElement extends Entity {

	// visibility and whether or not the mouse has entered
	private boolean visible;
	private boolean mouseEntered;

	public UIElement(GameState gameState) {
		super(gameState);

		visible = true;
		mouseEntered = false;
	}

	/** sets the x, y, width, and height of the element */
	public final void setBounds(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		setPositionRelativeToAnchor();
	}

	/** sets the position of the element at x, y + the anchor point */
	public final void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		setPositionRelativeToAnchor();
	}
	
	/** sets the position without using the anchor point */
	public final void setPositionAbsolute(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/** sets the position of the element relative to its anchor point */
	private void setPositionRelativeToAnchor() {
//		Point anchorPos = anchor.toPoint();
//		x += anchorPos.x;
//		y += anchorPos.y;
	}

	/** sets the x coordinate of the element relative to its anchor point */
	private void setXRelativeToAnchor() {
//		Point anchorPos = anchor.toPoint();
//		x += anchorPos.x;
	}

	/** sets the y coordinate of the element relative to its anchor point */
	private void setYRelativeToAnchor() {
//		Point anchorPos = anchor.toPoint();
//		y += anchorPos.y;
	}
	
	@Override
	public void update(float dt) {
		
		// do nothing if it's invisible
		if (!visible) {
			mouseEntered = false;
			return;
		}
			
		// check if the mouse moved
		if (Input.mouseMoved()) {
				
			// check if the mouse is within the ui element
			boolean inRect = Mathf.pointWithinRect(Input.mousePos(), getBounds());
			
			// if the current position is within the element but isn't set in the
			// mouse listener, set it
			if (!mouseEntered && inRect) {
				mouseEntered = true;
				for (Action a : listeners)
					if (a instanceof Action.OnMouseEnterListener)
						a.onAction();
			}
			
			// if the current position is outside the element but is currently set,
			// reset it
			else if (mouseEntered && !inRect) {
				mouseEntered = false;
				for (Action a : listeners)
					if (a instanceof Action.OnMouseExitListener)
						a.onAction();
			}
			
		}
		
		// check if the on click listener should be used
		if (mouseEntered && Input.mouseClicked())
			for (Action a : listeners)
				if (a instanceof Action.OnMouseClickListener)
					a.onAction();
	}

	/** is the element visible? */
	public final boolean isVisible() {
		return visible;
	}
	
	/** is the mouse within the bounds of the element? */
	public final boolean isMouseEntered() {
		return mouseEntered;
	}
	
	/** returns the bounds of the element */
	public final Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, (int)width, (int)height);
	}
	
	/** sets the x value to the x passed + the anchor point's x value */
	public void setX(float x) {
		this.x = x;
		setXRelativeToAnchor();
	}

	/** sets the y value to the y passed + the anchor point's y value */
	public void setY(float y) {
		this.y = y;
		setYRelativeToAnchor();
	}

	/** sets the visibility of the element */
	public void setVisible(boolean visible) {
		this.visible = visible;
		if (!visible)
			mouseEntered = false;
	}
}
