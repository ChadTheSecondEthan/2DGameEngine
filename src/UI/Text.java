package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import GameState.GameState;

public class Text extends UIElement {
	
	public enum TextAlign { LEFT, CENTER, RIGHT }
	
	protected String text;
	protected Font font;
	protected Color color;
	protected TextAlign alignment;

	public Text(GameState gameState, String text) {
		super(gameState);
		this.text = text;
		
		font = new Font("Arial", Font.PLAIN, 12);
		color = Color.black;
		alignment = TextAlign.CENTER;
	}

	public void draw(Graphics g) {
		
		// set the font and color of the graphics
		g.setFont(font);
		g.setColor(color);
		
		// align the text based on the text alignment
		int drawX = (int) x;
		if (alignment != TextAlign.LEFT) {
			int width = g.getFontMetrics().stringWidth(text);
			
			if (alignment == TextAlign.RIGHT)
				drawX -= width;
			else 
				drawX -= width / 2;
		}
		
		// draw the string
		g.drawString(text, drawX, (int) y);
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

	public void setText(String text) {
		this.text = text;
	}

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
