package com.chad.engine.Entity;

import java.awt.*;

public class ColorRenderer implements Drawable {

    private Color color;

    public ColorRenderer(Color color) { this.color = color; }

    @Override
    public void draw(Entity e, Graphics g) {
        g.setColor(color);
        g.fillRect((int) e.getX(), (int) e.getY(), (int) e.width, (int) e.height);
    }

    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }
}
