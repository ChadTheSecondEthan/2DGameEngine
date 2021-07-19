package UI;

import Entity.*;
import Main.Game;
import Utils.Input;
import Utils.Mathf;

public class UIElement extends Entity {

	public static final byte ON_MOUSE_ENTER = 20;
	public static final byte ON_MOUSE_EXIT = 21;
	public static final byte ON_MOUSE_CLICK = 22;

	// visibility and whether or not the mouse has entered
	private boolean mouseEntered;

	public UIElement() {
		super();

		mouseEntered = false;
	}

	public void centerX() {
	    setX((Game.instance().getWindowSize().width - width) * 0.5f);
    }

    public void centerY() {
        setY((Game.instance().getWindowSize().height - height) * 0.5f);
    }

    public static float centerX(float width) {
	    return (Game.instance().getWindowSize().width - width) * 0.5f;
    }

    public static float centerY(float height) {
        return (Game.instance().getWindowSize().height - height) * 0.5f;
    }
	
	@Override
	public void update(float dt) {
		
		// do nothing if it's invisible
		if (!visible)
			return;
			
		// check if the mouse moved
		if (Input.mouseMoved()) {
				
			// check if the mouse is within the ui element
			boolean inRect = Mathf.pointWithinRect(Input.mousePos(), getBounds());
			
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
		if (mouseEntered && Input.mouseClicked())
			invokeListeners(ON_MOUSE_CLICK);
	}
	
	/** is the mouse within the bounds of the element? */
	public final boolean mouseWithinBounds() {
		return mouseEntered;
	}

	/** sets the visibility of the element */
	@Override
	public void setVisible(boolean visible) { mouseEntered = (this.visible = visible) && mouseEntered; }

	public Listener addMouseHoverListener(Action action) { return addListener(ON_MOUSE_ENTER, action); }
	public Listener addMouseExitListener(Action action) { return addListener(ON_MOUSE_EXIT, action); }
	public Listener addOnClickListener(Action action) { return addListener(ON_MOUSE_CLICK, action); }
}
