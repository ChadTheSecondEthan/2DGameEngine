package Entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import GameState.GameState;

public abstract class Entity {

	// listeners
	protected ArrayList<Action> listeners;
	
	// remember the game state the entity is in
	protected GameState gameState;

	// drawable for the entity
	protected Drawable drawable;
	
	// bounds
	protected float x, y;
	protected float width, height;

	public Entity(GameState gameState) {
		this.gameState = gameState;

		listeners = new ArrayList<>();
	}
	
	/** destroys this entity */
	public void destroy() {
		
		// use on destroy listeners
		for (Action a : listeners)
			if (a instanceof Action.OnDestroyListener)
				a.onAction();
		
		// remove it from the game state
		gameState.remove(this); 
	}
	
	/** checks if the entity is touching another one */
	public boolean intersects(Entity other) {
		return x + width > other.x && x < other.x + other.width &&
				y + height > other.y && y < other.y + other.height;
	}
	
	/** updates the entity with the change in time since the last frame */
	public abstract void update(float dt);
	
	/** draws the entity onto the graphics */
	public void draw(Graphics g) { drawable.draw(this, g); }
	
	/** set position */
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float getX() { return x; }
	public float getY() { return y; }
	public float getWidth() {
		return width;
	}
	public float getHeight() {
		return height;
	}

	/** returns the current image for this entity */
	public BufferedImage getCurrentImage() { return drawable.getImage(); }

	public void setX(float x) { this.x = x; }
	public void setY(float y) { this.y = y; }
	public void setWidth(float width) { this.width = width; }
	public void setHeight(float height) { this.height = height; }

	/** adds a listener to the entity */
	public void addListener(Action listener) { listeners.add(listener); }

}
