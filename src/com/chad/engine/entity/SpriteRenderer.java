package com.chad.engine.entity;

import com.chad.engine.gfx.Renderer;
import com.chad.engine.utils.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpriteRenderer implements Drawable {

    private BufferedImage image;

    public SpriteRenderer(BufferedImage image) {
        this.image = image;
    }

    public SpriteRenderer(String imgPath) {
        if (imgPath != null)
            image = Resources.loadImage(imgPath);
    }

    public SpriteRenderer() {}

    public void draw(Entity e) {
        Renderer.draw(image, e.getX(), e.getY(), e.width, e.height);
    }

    public BufferedImage getImage() { return image; }
    public void setImage(BufferedImage image) { this.image = image; }
    public void setImage(String imgPath) { image = Resources.loadImage(imgPath); }
}
