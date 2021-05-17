package GameState;

import java.awt.Graphics;
import java.util.ArrayList;

import Entity.Entity;
import Main.GameLoop;

public abstract class GameState {
	
	// entities
	protected ArrayList<Entity> entities;

	// the game loop
	protected GameLoop gameLoop;

	public GameState(GameLoop gameLoop) {
		this.gameLoop = gameLoop;

		entities = new ArrayList<>();
	}
	
	// spawns an entity
	public void spawn(Entity e) { entities.add(e); }
	
	// removes an entity
	public void remove(Entity e) { entities.remove(e); }
	
	// update the entities with the change in time since the last frame
	public void update(float dt) {
		
		// create a copy of the list in case an entity is removed this frame
		ArrayList<Entity> entityCopy = new ArrayList<>(entities);
		for (Entity e : entityCopy)
			e.update(dt);
	}
	
	// draw the game objects with the given graphics
	public void draw(Graphics g) {

		// create a copy of the list in case an entity is removed this frame
		ArrayList<Entity> entityCopy = new ArrayList<>(entities);
		for (Entity e : entityCopy)
			e.draw(g);
	}
	
	/** returns the entities */
	public ArrayList<Entity> getEntities() { return entities; }

	/** returns the game loop */
	public GameLoop getGameLoop() { return gameLoop; }
}
