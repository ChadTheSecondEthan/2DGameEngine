package com.chad.engine.ui;

import com.chad.engine.entity.Entity;
import com.chad.engine.gfx.Renderer;

import java.awt.Color;

public class ColorRenderer extends UIElement {

    private Color color;

    public ColorRenderer() { this(Color.black);}

    public ColorRenderer(Color color) {
        super();

        this.color = color;
        this.drawable = this::draw;
    }

    private void draw(Entity e) {
        Renderer.setColor(color);
        Renderer.fill(e.getX(), e.getY(), e.getWidth(), e.getHeight());
    }

    public void setColor(Color color) { this.color = color; }
    public Color getColor() { return color; }
}
