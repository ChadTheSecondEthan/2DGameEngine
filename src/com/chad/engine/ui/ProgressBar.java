package com.chad.engine.ui;

import com.chad.engine.gfx.Renderer;

import java.awt.Color;

public class ProgressBar extends UIElement {
	
	private float progress;
	private int fillWidth;
	private int minDrawWidth;
	private Color fillColor;
	private Color backgroundColor;

	public ProgressBar() {
		super();
		height = 20;
		
		progress = 0;
		fillWidth = 2;
		minDrawWidth = 5;
		fillColor = Color.red;
		backgroundColor = Color.black;
	}

	@Override
	public void draw() {
		Renderer.setColor(backgroundColor);
		Renderer.fill(getX(), getY(), width, height);
		Renderer.setColor(fillColor);
		
		int drawWidth = (int) ((width - fillWidth * 2) * progress);
		if (drawWidth < minDrawWidth)
			drawWidth = minDrawWidth;
		Renderer.fill((int)getX() + fillWidth, (int)getY() + fillWidth, drawWidth, ((int)height - fillWidth * 2));
	}

	@Override
	public boolean setAttribute(String attr, String value) throws Exception {
		if (attr.equals("progress")) {
			setProgress(Float.parseFloat(value));
			return true;
		}

		return super.setAttribute(attr, value);
	}

	public double getProgress() {
		return progress;
	}

	public int getFillWidth() {
		return fillWidth;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setProgress(double progress) {
		if (progress <= 1 && progress >= 0)
			this.progress = (float) progress;
	}

	public void setFillWidth(int fillWidth) {
		this.fillWidth = fillWidth;
	}
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public int getMinDrawWidth() {
		return minDrawWidth;
	}

	public void setMinDrawWidth(int minDrawWidth) {
		this.minDrawWidth = minDrawWidth;
	}

}
