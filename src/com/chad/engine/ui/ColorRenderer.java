package com.chad.engine.ui;

import com.chad.engine.entity.Drawable;
import com.chad.engine.entity.Entity;
import com.chad.engine.gfx.Renderer;

import java.awt.Color;

public class ColorRenderer implements Drawable {

    private Color color;

    public ColorRenderer() { this(Color.black);}

    public ColorRenderer(Color color) {
        super();

        this.color = color;
    }

    @Override
    public void draw(Entity e) {
        Renderer.setColor(color);
        Renderer.fill(e.getX(), e.getY(), e.getWidth(), e.getHeight());
    }
}
