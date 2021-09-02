package com.chad.engine.ui;

import com.chad.engine.gfx.Renderer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

@SuppressWarnings({"WeakerAccess", "unused"})
public class Text extends UIElement {

	public static Font defaultFont = new Font("Arial", Font.PLAIN, 12);
	public static Color defaultColor = Color.black;
	
	public enum TextAlign { LEFT, CENTER, RIGHT }
	
	protected String text;
	protected Font font;
	protected Color color;
	protected TextAlign alignment;

	public Text() { this(""); }

	public Text(String text) {
		super();
		this.text = text;
		
		font = defaultFont;
		color = defaultColor;
		alignment = TextAlign.CENTER;
	}

	@Override
	public void draw() {
		
		// set the font and color of the graphics
		Renderer.setFont(font);
		Renderer.setColor(color);
		
		// align the text based on the text alignment
		int drawX = (int) getX();
		if (alignment != TextAlign.LEFT) {
			int width = Renderer.getFontMetrics().stringWidth(text);
			drawX -= alignment == TextAlign.RIGHT ? width : (width / 2);
		}
		
		// draw the string
		Renderer.drawString(text, drawX, (int) getY());
	}
	
	/**
	 * 
	 * 
	 * 
	 * Getters and Setters
	 * 
	 * 
	 * 
	 */
	
	public String getText() {
		return text;
	}

	public void setText(String text) { this.text = text; }

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public TextAlign getAlignment() {
		return alignment;
	}

	public void setAlignment(TextAlign alignment) {
		this.alignment = alignment;
	}

}
