package com.chad.engine.Entity;

import java.awt.*;
import java.util.ArrayList;

import com.chad.engine.GameState.GameState;
import com.chad.engine.Utils.GameFile;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;

@SuppressWarnings({"WeakerAccess", "unused", "unchecked"})
public abstract class Entity {

	private static int ID = 0;

	public static final byte DESTROY = 0;
	public static final byte SPAWN = 1;
	public static final byte DESPAWN = 2;

	// listeners
	private ArrayList<Listener> listeners;

	// drawable for the entity
	protected Drawable drawable;
	
	// bounds
	private float x, y;
	protected float width, height;

	// visibility
	protected boolean visible;

	// parent
	private Entity parent;

	// misc
	private int id;
	private int zIndex;
	private String name;
	private boolean useListeners;

	public Entity() {
		id = ID;
		ID++;
		zIndex = 0;
		visible = true;

		listeners = new ArrayList<>();
		useListeners = true;

		parent = null;
	}
	
	/** destroys this entity */
	public void destroy() {

		invokeListeners(DESTROY);
		
		// remove it from the game state
		GameState.current().remove(this);
	}

	/** spawns the entity */
	public void spawn() { GameState.current().spawn(this); }

	/** removes the entity and its children */
	public void despawn() {
		GameState.current().remove(this);
		for (Entity e : getChildrenList())
			e.despawn();
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

	/** returns the bounds of the element */
	public final Rectangle getBounds() {
		return new Rectangle((int)getX(), (int)getY(), (int)width, (int)height);
	}

	/** adds a listener to the entity */
	public Listener addListener(Listener listener) { listeners.add(listener); return listener; }
	public Listener addListener(byte type, Action action) {
		Listener l = new Listener();
		l.type = type;
		l.action = action;
		listeners.add(l);
		return l;
	}

	/** returns true if an attribute was successfully set */
	public boolean setAttribute(String attr, String value) {
		switch(attr.toLowerCase()) {
			case "x":
				setX(Float.parseFloat(value));
				return true;
			case "y":
				setY(Float.parseFloat(value));
				return true;
			case "z":
				setzIndex(Integer.parseInt(value));
				return true;
			case "width":
				setWidth(Float.parseFloat(value));
				return true;
			case "height":
				setHeight(Float.parseFloat(value));
				return true;
			case "name":
				setName(value);
				return true;
			case "visible":
				setVisible(Boolean.parseBoolean(value));
				return true;
		}
		return false;
	}

	public static <T extends Entity> T fromFile(String path) {
		GameFile file = new GameFile(path);

		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
			doc.getDocumentElement().normalize();
			Entity entity = (Entity) Class.forName(doc.getDocumentElement().getNodeName()).newInstance();

			NodeList children = doc.getDocumentElement().getChildNodes();
			if (children != null && children.getLength() != 0)
				createChildren(entity, children);

			return (T) entity;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void createChildren(Entity parent, NodeList children) throws Exception {
		for (int temp = 0; temp < children.getLength(); temp++) {
			Node node = children.item(temp);
			if (node.getNodeType() != Node.ELEMENT_NODE)
				continue;

			Entity entity = (Entity) Class.forName("com.chad.engine." + node.getNodeName()).newInstance();
			entity.setParent(parent);
			entity.spawn();

			if (entity instanceof com.chad.engine.UI.Text)
				((com.chad.engine.UI.Text) entity).setText(node.getTextContent());

			if (node.getChildNodes() != null)
				createChildren(entity, node.getChildNodes());

			NamedNodeMap map = node.getAttributes();

			for (int i = 0; i < map.getLength(); i++)
				if (!entity.setAttribute(map.item(i).getNodeName(), map.item(i).getNodeValue()))
					System.out.println("Invalid attribute: " + map.item(i).getNodeName());
		}
	}

	public Listener addDestroyListener(Action action) { return addListener(DESTROY, action); }
	public Listener addSpawnListener(Action action) { return addListener(SPAWN, action); }
	public Listener addDespawnListener(Action action) { return addListener(DESPAWN, action); }

	// invoking listeners
	public void invokeListeners(int type) {
		if (useListeners)
			for (Listener l : listeners)
				if (l.type == type)
					l.action.invoke();
	}

	public void invokeListener(int type) {
		if (useListeners)
			for (Listener l : listeners)
				if (l.type == type) {
					l.action.invoke();
					return;
				}
	}

	// removing listeners
	public void removeListeners(int type) {
		if (useListeners)
			for (int i = listeners.size() - 1; i >= 0; i--)
				if (listeners.get(i).type == type)
					listeners.remove(i);
	}

	public void removeListener(int type) {
		if (useListeners)
			for (int i = listeners.size() - 1; i >= 0; i--)
				if (listeners.get(i).type == type) {
					listeners.remove(i);
					return;
				}
	}

	public void removeListener(Listener listener) {
		if (useListeners)
			listeners.remove(listener);
	}

	// invoking and removing listeners
	public void invokeAndRemoveListeners(int type) {
		if (!useListeners) return;
		invokeListeners(type);
		removeListeners(type);
	}

	public void invokeAndRemoveListener(int type) {
		if (!useListeners) return;
		invokeListener(type);
		removeListener(type);
	}

	public void setParent(Entity parent) {
		if (parent == null) {
			if (this.parent != null)
				this.parent.removeChild(this);
		} else
			parent.addChild(this);
	}

	public Entity getParent() { return parent; }

	public void addChild(Entity child) { child.parent = this; }
	public void removeChild(Entity child) { child.parent = null; }

	public <T extends Entity> T getChild(String name) {
		for (Entity e : getChildrenList()) {
			String eName = e.getName();
			if (eName != null && eName.equals(name))
				return (T) e;
		}
		return null;
	}

	public void removeChildren() {
		for (Entity e : getChildrenList())
			e.setParent(null);
	}

	public Entity[] getChildrenArray() { return getChildrenList().toArray(new Entity[0]); }

	public ArrayList<Entity> getChildrenList() {
		ArrayList<Entity> children = new ArrayList<>();
		for (Entity e : GameState.current().getEntities())
			if (e.parent == this)
				children.add(e);

		return children;
	}

	/** set position relative to parent */
	public void setPosition(float x, float y) {
		setX(x);
		setY(y);
	}

	/** set position in screen coordinates */
	public void setAbsolutePosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/** sets the x, y, width, and height of the element */
	public void setBounds(float x, float y, float width, float height) {
		setX(x);
		setY(y);
		this.width = width;
		this.height = height;
	}

	/** sets the x, y, width, and height of the element */
	public final void setAbsoluteBounds(float x, float y, float width, float height) {
		setBounds(x - this.x, y - this.y, width, height);
	}

	public final float getX() { return x + (parent == null ? 0 : parent.getX()); }
	public final float getY() { return y + (parent == null ? 0 : parent.getY()); }

	public final float getRelativeX() { return x; }
	public final float getRelativeY() { return y; }

	public final float getAbsoluteX() { return getX(); }
	public final float getAbsoluteY() { return getY(); }

	public final float getWidth() { return width; }
	public final float getHeight() { return height; }

	public final void setX(float x) { this.x = x; }
	public final void setY(float y) { this.y = y; }

	public final void setRelativeX(float x) {
		setX(x);
	}
	public final void setRelativeY(float y) {
		setY(y);
	}

	public final void setAbsoluteX(float x) {
		this.x = x - (parent == null ? 0 : parent.getX());
	}
	public final void setAbsoluteY(float y) {
		this.y = y - (parent == null ? 0 : parent.getY());
	}

	public final void setWidth(float width) {
		this.width = width;
	}
	public final void setHeight(float height) {
		this.height = height;
	}

	public int getzIndex() { return zIndex; }
	public void setzIndex(int zIndex) {
		this.zIndex = zIndex;
		GameState.current().sortEntitiesByZIndex();
	}

	public final boolean isVisible() { return visible; }
	public void setVisible(boolean visible) { this.visible = visible; }

	public void setDrawable(Drawable d) { drawable = d; }
	public Drawable getDrawable() { return drawable; }

	public int getId() { return id; }
	public void generateNewId() { this.id = ID; ID++; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public boolean usesListeners() { return useListeners; }
	public void setUseListeners(boolean useListeners) { this.useListeners = useListeners; }
}
