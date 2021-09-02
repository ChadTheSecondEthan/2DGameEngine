package com.chad.engine.gameState;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import com.chad.engine.entity.Entity;
import com.chad.engine.entity.Listener;
import com.chad.engine.entity.Action;
import com.chad.engine.Game;
import com.chad.engine.utils.GameFile;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;

@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class GameState {

	public static final byte ON_STATE_CHANGE = 0;

	// the current game state
    private static GameState current = null;

	// listeners
	protected ArrayList<Listener> listeners;
	
	// entities
	protected ArrayList<Entity> entities;

	public GameState(String file) {
		this();

		readEntitiesFromFile(file);
	}

	public GameState() {

		if (Game.instance.isStarted())
			new Exception("Do not create new gamestate objects after that game has started. This results in a memory leak").printStackTrace();

		current = this;

		listeners = new ArrayList<>();
		entities = new ArrayList<>();
	}

	public void setState(String state) { Game.instance.getGameLoop().setState(state); }
	public void setState(int state) { Game.instance.getGameLoop().setState(state); }
	
	// spawns an entity
	public void spawn(Entity e) {
		entities.add(e);
		e.invokeListeners(Entity.SPAWN);
	}

	public void spawn(Entity... e) {
		entities.addAll(Arrays.asList(e));
		for (Entity ee : e)
			ee.invokeListeners(Entity.SPAWN);
	}
	
	// removes an entity
	public void remove(Entity e) {
		entities.remove(e);
		e.invokeListeners(Entity.DESPAWN);
	}
	
	// update the entities with the change in time since the last frame
	public void update(float dt) {

		// create a copy of the list in case an entity is removed this frame
		ArrayList<Entity> entityCopy = new ArrayList<>(entities);
		for (Entity e : entityCopy)
			e.update(dt);
	}

	// called when the state is set to this one
	public abstract void init();
	
	// draw the game objects with the given graphics
	public void draw() {

		// create a copy of the list in case an entity is removed this frame
		ArrayList<Entity> entityCopy = new ArrayList<>(entities);
		for (Entity e : entityCopy)
			if (e.isVisible())
				e.draw();
	}
	
	/** returns the entities */
	public ArrayList<Entity> getEntities() { return entities; }

	public void sortEntitiesByZIndex() {
		entities.sort(Comparator.comparingInt(left -> left.getzIndex()));
	}

	@SuppressWarnings("unchecked")
	public <T extends Entity> T findEntityById(int id) {
		for (Entity entity : entities) {
			if (entity.getId() == id)
				return (T) entity;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public <T extends Entity> T findEntityByName(String name) {
		for (Entity entity : entities) {
			String eName = entity.getName();
			if (eName != null && eName.equals(name))
				return (T) entity;
		}
		return null;
	}

	public void readEntitiesFromFile(String fileName) {
		GameFile file = new GameFile(fileName);

		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
			doc.getDocumentElement().normalize();
			NodeList children = doc.getDocumentElement().getChildNodes();
			Entity.createChildren(null, children);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** adds a listener to the entity */
	public void addListener(Listener listener) { listeners.add(listener); }
	public void addListener(byte type, Action action) {
		Listener l = new Listener();
		l.type = type;
		l.action = action;
		listeners.add(l);
	}

	// invoking listeners
	public void invokeListeners(int type) {
		for (Listener l : listeners)
			if (l.type == type)
				l.action.invoke();
	}

	public void invokeListener(int type) {
		for (Listener l : listeners)
			if (l.type == type) {
				l.action.invoke();
				return;
			}
	}

	public void invokeListener(Listener l) {
		for (Listener li : listeners)
			if (li == l) {
				l.action.invoke();
				return;
			}
	}

	// removing listeners
	public void removeListeners(int type) {
		for (int i = listeners.size() - 1; i >= 0; i--)
			if (listeners.get(i).type == type)
				listeners.remove(i);
	}

	public void removeListener(int type) {
		for (int i = listeners.size() - 1; i >= 0; i--)
			if (listeners.get(i).type == type) {
				listeners.remove(i);
				return;
			}
	}

	public void removeListener(Listener l) {
		for (int i = listeners.size() - 1; i >= 0; i--)
			if (listeners.get(i) == l) {
				listeners.remove(i);
				return;
			}
	}

	// invoking and removing listeners
	public void invokeAndRemoveListeners(int type) {
		invokeListeners(type);
		removeListeners(type);
	}

	public void invokeAndRemoveListener(int type) {
		invokeListener(type);
		removeListener(type);
	}

	public void invokeAndRemoveListener(Listener l) {
		invokeListener(l);
		removeListener(l);
	}

	public static GameState current() { return current; }
	public static void setCurrent(GameState state) { current = state; }
}
