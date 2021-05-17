package UI;

import java.awt.Color;
import java.awt.Graphics;

import GameState.GameState;

public class ProgressBar extends UIElement {
	
	private float progress;
	private int fillWidth;
	private int minDrawWidth;
	private Color fillColor;
	private Color backgroundColor;

	public ProgressBar(GameState gameState) {
		super(gameState);
		height = 20;
		
		progress = 0;
		fillWidth = 2;
		minDrawWidth = 5;
		fillColor = Color.red;
		backgroundColor = Color.black;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(backgroundColor);
		g.fillRect((int) x, (int) y, (int)width, (int)height);
		g.setColor(fillColor);
		
		int drawWidth = (int) ((width - fillWidth * 2) * progress);
		if (drawWidth < minDrawWidth)
			drawWidth = minDrawWidth;
		g.fillRect((int) x + fillWidth, (int) y + fillWidth, drawWidth, ((int)height - fillWidth * 2));
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
