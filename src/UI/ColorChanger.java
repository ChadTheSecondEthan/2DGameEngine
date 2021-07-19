package UI;

import java.awt.Color;
import java.awt.Graphics;

import Entity.Action;
import Entity.Drawable;
import Entity.Entity;
import GameState.GameState;
import Main.Game;
import Utils.Input;
import Utils.Mathf;

public class ColorChanger implements Drawable {

	private Color curColor;
	private Color lerpColor;

	public ColorChanger(Entity e, Color startColor) {
		super();

		curColor = lerpColor = startColor;
	}

	@Override
	public void draw(Entity e, Graphics g) {
		float dt = Game.instance().getGameLoop().deltaTime();
		if (dt > 0.1f) dt = 0.1f;
		curColor = Mathf.lerpColor(curColor, lerpColor, dt * 10);

		g.setColor(curColor);
		g.fillRect((int) e.getX(), (int) e.getY(), (int) e.getWidth(), (int) e.getHeight());
	}

	public void setCurrentColor(Color curColor) { this.curColor = curColor; }
	public void setColor(Color color) { this.lerpColor = color; }
}
