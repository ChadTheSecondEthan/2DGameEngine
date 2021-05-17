package UI;

import java.awt.Color;
import java.awt.Graphics;

import Entity.Action;
import GameState.GameState;
import Utils.Input;
import Utils.Mathf;

public class Button extends UIElement {
	
	Color mainColor;
	Color hoverColor;
	Color clickColor;
	Color curColor;
	Color lerpColor;

	public Button(GameState gameState) {
		super(gameState);
		
		width = 200;
		height = 50;
		
		mainColor = Color.LIGHT_GRAY;
		hoverColor = Color.GRAY;
		clickColor = Color.DARK_GRAY;
		
		curColor = mainColor;
		lerpColor = curColor;
		
		addListener(Action.onMouseEnter(() -> lerpColor = hoverColor));
		addListener(Action.onMouseExit(() -> lerpColor = mainColor));
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
		
		if (isMouseEntered()) {
			if (Input.mousePressed())
				lerpColor = clickColor;
			else 
				lerpColor = hoverColor;
		}
		
		if (dt > 0.1f) dt = 0.1f;
		curColor = Mathf.lerpColor(curColor, lerpColor, dt * 10);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(curColor);
		g.fillRect((int) x, (int) y, (int)width, (int)height);
	}
	
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (!visible)
			curColor = lerpColor = mainColor;
	}
	
	/** sets the colors for this button
	 * @param mainColor the default color of the button
	 * @param hoverColor the color when the user hovers over the button
	 * @param clickColor the color when the user clicks on the button
	 */
	public void setColors(Color mainColor, Color hoverColor, Color clickColor) {
		this.mainColor = mainColor;
		this.hoverColor = hoverColor;
		this.clickColor = clickColor;
	}
	
	public void setMainColor(Color mainColor) {
		this.mainColor = mainColor;
	}
	
	public void setHoverColor(Color hoverColor) {
		this.hoverColor = hoverColor;
	}
	
	public void setClickColor(Color clickColor) {
		this.clickColor = clickColor;
	}
}
