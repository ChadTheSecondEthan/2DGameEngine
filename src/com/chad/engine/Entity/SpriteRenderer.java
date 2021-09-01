package com.chad.engine.Entity;

import com.chad.engine.Utils.Resources;

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

    public void draw(Entity e, Graphics g) {
        g.drawImage(image, (int) e.getX(), (int) e.getY(), (int) e.width, (int) e.height, null);
    }

    public BufferedImage getImage() { return image; }
    public void setImage(BufferedImage image) { this.image = image; }
    public void setImage(String imgPath) { image = Resources.loadImage(imgPath); }
}
