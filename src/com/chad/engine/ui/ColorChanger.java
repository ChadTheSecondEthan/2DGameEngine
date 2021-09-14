package com.chad.engine.ui;

import java.awt.Color;
import java.awt.Graphics;

import com.chad.engine.Global;
import com.chad.engine.entity.Drawable;
import com.chad.engine.entity.Entity;
import com.chad.engine.Game;
import com.chad.engine.gfx.Renderer;
import com.chad.engine.utils.Mathf;

public class ColorChanger implements Drawable {

	private Color curColor;
	private Color lerpColor;

	public ColorChanger(Color startColor) {
		super();

		curColor = lerpColor = startColor;
	}

	@Override
	public void draw(Entity e) {;
		curColor = Mathf.lerpColor(curColor, lerpColor, Math.min(Global.dt, 0.1f) * 10);

		Renderer.setColor(curColor);
		Renderer.fill(e.getX(), e.getY(), e.getWidth(), e.getHeight());
	}

	public void setCurrentColor(Color curColor) { this.curColor = curColor; }
	public void setColor(Color color) { this.lerpColor = color; }
}
