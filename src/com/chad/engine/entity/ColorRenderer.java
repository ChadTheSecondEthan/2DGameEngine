package com.chad.engine.entity;

import com.chad.engine.gfx.Renderer;

import java.awt.*;

public class ColorRenderer implements Drawable {

    private Color color;

    public ColorRenderer(Color color) { this.color = color; }

    @Override
    public void draw(Entity e) {
        Renderer.setColor(color);
        Renderer.fill(e.getX(), e.getY(), e.width, e.height);
    }

    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }
}
